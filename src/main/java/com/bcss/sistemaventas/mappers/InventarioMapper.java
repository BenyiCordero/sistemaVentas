package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Inventario;
import com.bcss.sistemaventas.dto.response.InventarioBasicResponse;
import com.bcss.sistemaventas.dto.response.InventarioDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioBasicResponse mapToBasic(Inventario inventario) {
        return new InventarioBasicResponse(
            inventario.getIdInventario(),
            inventario.getTitulo(),
            inventario.getDescripcion(),
            inventario.getFechaCreacion(),
            inventario.getSucursal().getIdSucursal(),
            inventario.getSucursal().getNombre()
        );
    }

    public InventarioDetailResponse mapToDetail(Inventario inventario) {
        return new InventarioDetailResponse(
            inventario.getIdInventario(),
            inventario.getTitulo(),
            inventario.getDescripcion(),
            inventario.getFechaCreacion(),
            inventario.getSucursal().getIdSucursal(),
            inventario.getSucursal().getNombre()
        );
    }
}