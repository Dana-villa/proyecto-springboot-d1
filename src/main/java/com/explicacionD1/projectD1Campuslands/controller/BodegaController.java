package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.model.Bodega;
import com.explicacionD1.projectD1Campuslands.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bodegas") // Todas las URLs empezarán con http://localhost:8080/bodegas
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public List<Bodega> listar() {
        return bodegaService.obtenerTodas();
    }

    @PostMapping
    public Bodega crear(@RequestBody Bodega bodega) {
        return bodegaService.guardar(bodega);
    }

    @GetMapping("/{id}")
    public Bodega obtener(@PathVariable Long id) {
        return bodegaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        bodegaService.eliminar(id);
    }
}