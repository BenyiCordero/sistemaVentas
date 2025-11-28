package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
    @ManyToOne
    @JoinColumn(name = "idCredito", referencedColumnName = "idCredito", nullable = false)
    private Credito credito;
    @Column(nullable = false)
    private Double monto;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumMetodoPago metodoPago;
    @Column(nullable = false)
    private LocalDateTime fechaPago;
    @Column(length = 200)
    private String notas;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (fechaPago == null) fechaPago = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}