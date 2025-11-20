package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Sucursal;

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
}
