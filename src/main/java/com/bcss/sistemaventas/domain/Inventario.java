package com.bcss.sistemaventas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInventario;
    @Column(nullable = false, length = 60)
    private String titulo;
    @Column(nullable = false, length = 10)
    private String descripcion;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSucursal", referencedColumnName = "idSucursal")
    @JsonBackReference("sucursal-inventario")
    private Sucursal sucursal;
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventarioDetails> detalles;

}
