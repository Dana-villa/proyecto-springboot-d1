package com.explicacionD1.projectD1Campuslands.model;

import com.explicacionD1.projectD1Campuslands.audit.AuditoriaListener;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditoriaListener.class) // Para que también se audite
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    private String usuario; // Lo llenaremos con el token JWT
    private Integer cantidad;

    // Relaciones con otras tablas
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "bodega_origen_id", nullable = true)
    private Bodega bodegaOrigen;

    @ManyToOne
    @JoinColumn(name = "bodega_destino_id", nullable = true)
    private Bodega bodegaDestino;
}