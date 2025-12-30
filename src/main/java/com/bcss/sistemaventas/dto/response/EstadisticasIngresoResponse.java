package com.bcss.sistemaventas.dto.response;

public record EstadisticasIngresoResponse(
    Float totalVentas,
    Float ventasContado,
    Float ventasCredito,
    Float pagosRecibidosCreditos,
    Float ingresoTotal,
    String periodo
) {}