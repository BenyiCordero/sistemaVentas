package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 300, nullable = false)
    private String descripcion;
    @Column(length = 50, nullable = false)
    private String marca;
    @Column(length = 50, nullable = false)
    private String modelo;
    @Column(length = 15, nullable = true)
    private String imei;
    @Column(nullable = true)
    private Float precio;
    @Column(nullable = true)
    private Float costo;
    @Column(nullable = false)
    private Boolean activo;

}
