package com.bcss.sistemaventas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "Persona")
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String primerApellido;
    @Column(length = 50)
    private String segundoApellido;
    @Column(nullable = false, length = 12, unique = true)
    private String numeroTelefono;

}
