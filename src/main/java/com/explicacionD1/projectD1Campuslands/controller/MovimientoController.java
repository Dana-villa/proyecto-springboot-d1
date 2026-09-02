package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.exception.BusinessRuleException;
import com.explicacionD1.projectD1Campuslands.model.Movimiento;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.model.TipoMovimiento;
import com.explicacionD1.projectD1Campuslands.repository.MovimientoRepository;
import com.explicacionD1.projectD1Campuslands.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    // @Transactional asegura que si algo falla, no se guardan cambios a medias
    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {

        // 1. Buscar el producto en la base de datos
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado"));

        // 2. Lógica para actualizar el stock global del producto
        if (movimiento.getTipoMovimiento() == TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + movimiento.getCantidad());
        }
        else if (movimiento.getTipoMovimiento() == TipoMovimiento.SALIDA) {
            if (producto.getStock() < movimiento.getCantidad()) {
                throw new BusinessRuleException("Stock insuficiente para realizar la salida");
            }
            producto.setStock(producto.getStock() - movimiento.getCantidad());
        }
        else if (movimiento.getTipoMovimiento() == TipoMovimiento.TRASLADO) { // <-- Aquí ajustamos a TRASLADO
            // En una transferencia el stock global no cambia, solo se mueve de bodega.
            if (producto.getStock() < movimiento.getCantidad()) {
                throw new BusinessRuleException("Stock insuficiente para transferir");
            }
            if (movimiento.getBodegaOrigen() == null || movimiento.getBodegaDestino() == null) {
                throw new BusinessRuleException("Para transferencias, origen y destino son obligatorios");
            }
        }

        // Guardamos el nuevo stock del producto
        productoRepository.save(producto);

        // 3. Completar los datos automáticos del movimiento
        movimiento.setFecha(LocalDateTime.now());

        // Extraer el usuario logueado usando Spring Security
        String username = "Sistema"; // Valor por defecto por si no hay login
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        movimiento.setUsuario(username);

        // Guardar y retornar el registro del movimiento
        return movimientoRepository.save(movimiento);
    }
}