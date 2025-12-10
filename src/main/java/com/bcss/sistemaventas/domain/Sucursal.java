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
@Table(name = "Sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSucursal;
    @Column(nullable = false, length = 60)
    private String nombre;
    @Column(nullable = false, length = 10)
    private String sucursalKey;
    @Column(nullable = false)
    private Boolean activo;
    @OneToMany(mappedBy = "sucursal")
    private List<Trabajador> trabajadores;
    @OneToOne(mappedBy = "sucursal")
    private Inventario inventario;
}

