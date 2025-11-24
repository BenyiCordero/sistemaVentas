package com.bcss.sistemaventas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Venta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;
    @ManyToOne
    @JoinColumn(name = "idSucursal", referencedColumnName = "idSucursal")
    private Sucursal sucursal;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Trabajador trabajador;
    @Column(nullable = false)
    private LocalDate fechaVenta;
    @Column(nullable = false)
    private Double totalVenta;
    @Column(nullable = false)
    private Double descuento;
    @Column(nullable = false)
    private Double impuesto;
    @Column(nullable = false)
    private EnumEstadoVenta estado;
    @Column(nullable = false)
    private String notas;
}

