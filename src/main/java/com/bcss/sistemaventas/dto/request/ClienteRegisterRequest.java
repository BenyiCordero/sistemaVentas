package com.bcss.sistemaventas.dto.request;

public record ClienteRegisterRequest(
        //Persona
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        //Cliente
        Boolean creditoActivo
) {
}
