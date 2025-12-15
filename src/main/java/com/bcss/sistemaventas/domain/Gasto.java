package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Gasto")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGasto;
    @Column(nullable = false, length = 150)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumCategoriaGasto tipoGasto;
    @Column(nullable = false)
    private Float monto;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumMetodoPago metodoPago;
    @Column(nullable = true)
    private String proveedor;
    @Column(nullable = true)
    private String factura;
    @Column(nullable = true, length = 200)
    private String notas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;

}
