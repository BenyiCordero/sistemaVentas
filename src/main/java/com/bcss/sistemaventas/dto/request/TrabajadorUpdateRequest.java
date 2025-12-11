package com.bcss.sistemaventas.dto.request;

import com.bcss.sistemaventas.domain.Rol;

public record TrabajadorUpdateRequest(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        Rol rol,
        Integer idSucursal
) {
}
