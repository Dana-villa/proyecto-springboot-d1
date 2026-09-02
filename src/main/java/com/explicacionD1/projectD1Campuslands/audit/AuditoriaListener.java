package com.explicacionD1.projectD1Campuslands.audit;

import com.explicacionD1.projectD1Campuslands.model.Auditoria;
import com.explicacionD1.projectD1Campuslands.repository.AuditoriaRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AuditoriaListener {

    @Autowired
    @Lazy
    private AuditoriaRepository auditoriaRepository;

    @PostPersist
    public void despuesDeInsertar(Object entidad) {
        registrarAuditoria("INSERT", entidad);
    }

    @PostUpdate
    public void despuesDeActualizar(Object entidad) {
        registrarAuditoria("UPDATE", entidad);
    }

    @PreRemove
    public void antesDeEliminar(Object entidad) {
        registrarAuditoria("DELETE", entidad);
    }

    private void registrarAuditoria(String operacion, Object entidad) {
        Auditoria auditoria = new Auditoria();
        auditoria.setTipoOperacion(operacion);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setEntidadAfectada(entidad.getClass().getSimpleName());

        // 1. Obtener el usuario logueado desde tu JWT (SecurityContext)
        String usuario = "Sistema (Sin login)";
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        auditoria.setUsuario(usuario);

        // 2. Guardar el estado del objeto.
        if (operacion.equals("DELETE")) {
            auditoria.setValoresAnteriores(entidad.toString());
            auditoria.setValoresNuevos("N/A - Eliminado");
        } else if (operacion.equals("INSERT")) {
            auditoria.setValoresAnteriores("N/A - Nuevo Registro");
            auditoria.setValoresNuevos(entidad.toString());
        } else {
            auditoria.setValoresAnteriores("Estado previo (Revisar logs anteriores)");
            auditoria.setValoresNuevos(entidad.toString());
        }

        // Guardamos el registro en la base de datos
        auditoriaRepository.save(auditoria);
    }
}