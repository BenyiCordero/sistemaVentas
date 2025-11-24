package com.bcss.sistemaventas.dto.request;

import com.bcss.sistemaventas.domain.EnumEstadoVenta;

public record EstadoVentaRequest(
        EnumEstadoVenta estado
) {
}
