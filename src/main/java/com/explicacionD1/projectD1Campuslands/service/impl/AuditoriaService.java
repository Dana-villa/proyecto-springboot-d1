package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.model.Auditoria;
import com.explicacionD1.projectD1Campuslands.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public List<Auditoria> obtenerTodas() {
        return auditoriaRepository.findAll();
    }

    public Auditoria guardar(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }
}