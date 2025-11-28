package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record InventarioBasicResponse(
    Integer idInventario,
    String titulo,
    String descripcion,
    LocalDate fechaCreacion,
    Integer idSucursal,
    String sucursalNombre
) {}