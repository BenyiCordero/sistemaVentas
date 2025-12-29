package com.bcss.sistemaventas.domain;

import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tarjeta")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarjeta;
    @Column(length = 100, nullable = false)
    private String nombreTarjeta;
    @Column(nullable = false)
    private EnumTipoTarjeta tipoTarjeta;
    @Column(nullable = false)
    private String numeroTarjeta;

}
