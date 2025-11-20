package com.bcss.sistemaventas.domain;

import com.bcss.sistemaventas.auth.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

}
