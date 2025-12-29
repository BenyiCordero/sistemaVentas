package com.bcss.sistemaventas.dto.request;

import com.bcss.sistemaventas.domain.EnumTipoTarjeta;

public record TarjetaCreateRequest(
        String nombreTarjeta,
        EnumTipoTarjeta tipoTarjeta,
        String numeroTarjeta
) {
}
