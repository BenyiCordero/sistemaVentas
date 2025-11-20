package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Inventario;

import java.util.List;

public interface InventarioService {
    List<Inventario> getAll();
    Inventario save(Inventario inventario);
    Inventario getInventarioPerSucursal(Integer id);
}
