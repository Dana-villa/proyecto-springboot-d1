package com.explicacionD1.projectD1Campuslands.controller;

import com.explicacionD1.projectD1Campuslands.model.Auditoria;
import com.explicacionD1.projectD1Campuslands.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<Auditoria>> listarAuditorias() {
        return ResponseEntity.ok(auditoriaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Auditoria> crearAuditoria(@RequestBody Auditoria auditoria) {
        return ResponseEntity.ok(auditoriaService.guardar(auditoria));
    }
}