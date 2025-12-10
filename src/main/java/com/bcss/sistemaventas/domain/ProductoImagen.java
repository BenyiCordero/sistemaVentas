package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ProductoImagen")
public class ProductoImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagen;
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = false)
    private Producto producto;
    @Column(nullable = false, length = 500)
    private String urlImagen;
    @Column(nullable = false, length = 100)
    private String nombreArchivo;
    @Column(nullable = false)
    private Integer tamañoArchivo;
    @Column(length = 10)
    private String tipoArchivo;
    @Column(nullable = false)
    private Boolean esPrincipal;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (esPrincipal == null) esPrincipal = false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}