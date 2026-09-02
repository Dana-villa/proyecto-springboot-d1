package com.explicacionD1.projectD1Campuslands.service.impl;

import com.explicacionD1.projectD1Campuslands.model.Movimiento;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.repository.MovimientoRepository;
import com.explicacionD1.projectD1Campuslands.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Producto> obtenerProductosConStockBajo(Integer limite) {
        return productoRepository.findByStockLessThan(limite);
    }

    public List<Movimiento> obtenerMovimientosPorRango(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaBetween(inicio, fin);
    }
}