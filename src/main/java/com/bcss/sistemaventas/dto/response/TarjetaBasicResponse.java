package com.bcss.sistemaventas.dto.response;

import com.bcss.sistemaventas.domain.EnumTipoTarjeta;

public record TarjetaBasicResponse(
        String nombreTarjeta,
        EnumTipoTarjeta tipoTarjeta,
        String numeroTarjeta
) {
}
