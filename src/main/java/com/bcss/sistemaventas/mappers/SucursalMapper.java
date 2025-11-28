package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.dto.response.SucursalBasicResponse;
import com.bcss.sistemaventas.dto.response.SucursalDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public SucursalBasicResponse mapToBasic(Sucursal sucursal) {
        return new SucursalBasicResponse(
            sucursal.getIdSucursal(),
            sucursal.getNombre(),
            sucursal.getSucursalKey(),
            sucursal.getActivo()
        );
    }

    public SucursalDetailResponse mapToDetail(Sucursal sucursal) {
        return new SucursalDetailResponse(
            sucursal.getIdSucursal(),
            sucursal.getNombre(),
            sucursal.getSucursalKey(),
            sucursal.getActivo()
        );
    }
}