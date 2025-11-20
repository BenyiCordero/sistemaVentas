package com.bcss.sistemaventas.dto.request;

import com.bcss.sistemaventas.auth.Rol;

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
