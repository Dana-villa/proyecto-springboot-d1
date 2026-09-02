package com.explicacionD1.projectD1Campuslands.model;

import com.explicacionD1.projectD1Campuslands.config.SpringContext;
import com.explicacionD1.projectD1Campuslands.repository.AuditoriaRepository;
import jakarta.persistence.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;

public class AuditoriaListener {

    // Se ejecuta justo después de hacer un INSERT
    @PostPersist
    public void onPostPersist(Object entidad) {
        registrarAuditoria("INSERT", entidad);
    }

    // Se ejecuta justo después de hacer un UPDATE
    @PostUpdate
    public void onPostUpdate(Object entidad) {
        registrarAuditoria("UPDATE", entidad);
    }

    // Se ejecuta justo antes de hacer un DELETE
    @PreRemove
    public void onPreRemove(Object entidad) {
        registrarAuditoria("DELETE", entidad);
    }

    private void registrarAuditoria(String operacion, Object entidad) {
        // 1. Conseguimos el repositorio usando nuestro puente
        AuditoriaRepository auditoriaRepository = SpringContext.getBean(AuditoriaRepository.class);

        // 2. Extraemos el nombre del usuario logueado usando Spring Security
        String usuarioLogueado = "Desconocido";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            usuarioLogueado = auth.getName();
        }

        // 3. Construimos el registro
        Auditoria auditoria = new Auditoria();
        auditoria.setOperacion(operacion);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setUsuario(usuarioLogueado);
        auditoria.setEntidadAfectada(entidad.getClass().getSimpleName()); // Extrae el nombre de la clase (Ej: "Bodega")

        // Usamos .toString() proporcionado por @Data de Lombok para guardar los valores
        auditoria.setValores(entidad.toString());

        // 4. Guardamos en base de datos
        auditoriaRepository.save(auditoria);
    }
}