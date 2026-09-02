package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.model.Movimiento;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Si no se envía un número, el valor por defecto será 10
    @GetMapping("/stock-bajo")
    public List<Producto> stockBajo(@RequestParam(defaultValue = "10") Integer limite) {
        return reporteService.obtenerProductosConStockBajo(limite);
    }

    // Obligamos a que nos envíen una fecha de inicio y una de fin
    @GetMapping("/movimientos")
    public List<Movimiento> movimientosPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        return reporteService.obtenerMovimientosPorRango(inicio, fin);
    }
}