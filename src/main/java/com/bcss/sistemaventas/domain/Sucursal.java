package com.bcss.sistemaventas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    @JsonManagedReference("sucursal-trabajadores")
    private List<Trabajador> trabajadores;
    @OneToOne(mappedBy = "sucursal", fetch = FetchType.LAZY)
    @JsonManagedReference("sucursal-inventario")
    private Inventario inventario;
}

