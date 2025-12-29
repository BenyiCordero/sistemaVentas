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
    @Column(nullable = false)
    private Double monto;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumMetodoPago metodoPago;
    @Column(nullable = false)
    private LocalDateTime fechaPago;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarjeta")
    private Tarjeta tarjeta;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (fechaPago == null) fechaPago = LocalDateTime.now();
    }
}