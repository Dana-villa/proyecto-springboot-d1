package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
}