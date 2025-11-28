package com.bcss.sistemaventas.dto.response;

public record ProductoBasicResponse(
    Integer idProducto,
    String nombre,
    String marca,
    String modelo,
    Float precio,
    Boolean activo
) {}