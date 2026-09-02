package com.explicacionD1.projectD1Campuslands.model;

import com.explicacionD1.projectD1Campuslands.audit.AuditoriaListener;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditoriaListener.class) // <--- Activa la auditoría para Producto
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;
}