package com.bcss.sistemaventas.dto.response;

public record ProductoDetailResponse(
    Integer idProducto,
    String nombre,
    String descripcion,
    String marca,
    String modelo,
    String imei,
    Float precio,
    Float costo,
    Boolean activo
) {}