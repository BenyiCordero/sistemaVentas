package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.dto.request.SucursalPerUsuarioRequest;
import com.bcss.sistemaventas.dto.response.SucursalPerUsuarioResponse;

import java.util.List;

public interface SucursalService {
    Sucursal save(Sucursal sucursal);
    Sucursal findById(Integer id);
    Sucursal findByName(String name);
    List<Sucursal> getAllSucursales();
    Sucursal updateSucursal(Sucursal sucursal);
    void deleteSucursalById(Integer id);
    List<Sucursal> getAllActive();
    List<Sucursal> getAllInactive();
    SucursalPerUsuarioResponse getSucursalPerUsuario(SucursalPerUsuarioRequest email);
}
