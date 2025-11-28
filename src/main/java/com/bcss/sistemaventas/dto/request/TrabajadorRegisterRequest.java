package com.bcss.sistemaventas.dto.request;

import com.bcss.sistemaventas.domain.Rol;

public record TrabajadorRegisterRequest(
        //Persona
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        //Trabajador
        String email,
        String password
) {
}
