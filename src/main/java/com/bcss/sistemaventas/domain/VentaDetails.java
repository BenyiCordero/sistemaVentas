package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "VentaDetails")
public class VentaDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVentaDetails;
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    private Venta venta;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private Double subtotal;
}