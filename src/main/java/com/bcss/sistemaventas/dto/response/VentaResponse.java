package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record VentaResponse(
    Integer idVenta,
    LocalDate fechaVenta,
    Double totalVenta,
    Double descuento,
    Double impuesto,
    String estado,
    String notas,
    Integer idCliente,
    String clienteNombre,
    Integer idTrabajador,
    String trabajadorNombre,
    Integer idSucursal,
    String sucursalNombre
) {}