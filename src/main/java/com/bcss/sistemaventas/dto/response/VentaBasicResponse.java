package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record VentaBasicResponse(
    Integer idVenta,
    LocalDate fechaVenta,
    Double totalVenta,
    String estado,
    String clienteNombre,
    String trabajadorNombre,
    String sucursalNombre
) {}