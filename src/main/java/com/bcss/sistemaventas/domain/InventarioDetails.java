package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "InventarioDetails")
public class InventarioDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(nullable = false, length = 60)
    private String estado;
    @Column(nullable = false)
    private boolean disponible;
    @Column(nullable = false)
    private LocalDate fechaAgregado;
    @ManyToOne
    @JoinColumn(name = "idInventario", nullable = false)
    private Inventario inventario;
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
}
