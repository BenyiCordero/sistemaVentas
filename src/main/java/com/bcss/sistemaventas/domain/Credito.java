package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Credito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCredito;
    @OneToOne
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    private Venta venta;
    @Column(nullable = false)
    private Double montoInicial;
    @Column(nullable = false)
    private Double saldo;
    @Column(nullable = false)
    private Double tasaInteres;
    @Column(nullable = false)
    private Integer plazoMeses;
    @Column(nullable = false)
    private EnumEstadoCredito estado;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    @Column(nullable = false)
    private LocalDateTime createdAt;

@PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (tasaInteres == null) tasaInteres = 0.0;
        if (plazoMeses == null) plazoMeses = 0;
        if (estado == null) estado = EnumEstadoCredito.ACTIVO;
    }

    public boolean puedeRecibirPagos() {
        return estado == EnumEstadoCredito.ACTIVO && saldo > 0;
    }

    public boolean estaVencido() {
        return fechaVencimiento != null && fechaVencimiento.isBefore(LocalDate.now()) && saldo > 0;
    }

    public void aplicarPago(Double montoPago) {
        if (montoPago <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a 0");
        }
        if (montoPago > saldo) {
            throw new IllegalArgumentException("El monto del pago excede el saldo del crédito");
        }
        
        saldo -= montoPago;
        if (saldo <= 0) {
            saldo = 0.0;
            estado = EnumEstadoCredito.PAGADO;
        }
    }
}