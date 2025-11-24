package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.EnumEstadoVenta;
import com.bcss.sistemaventas.domain.Venta;
import com.bcss.sistemaventas.dto.request.VentaRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VentaService {
    List<Venta> getAll(@RequestParam(required = false) EnumEstadoVenta estado);
    Venta getById(Integer id);
    Venta save(VentaRequest venta);
    Venta update(Integer id, VentaRequest request);
    void delete(Integer id);
    List<Venta> getBySucursal(Integer idSucursal);
    List<Venta> getByCliente(Integer idCliente);
    List<Venta> getByTrabajador(Integer idTrabajador);
    Venta changeStatus(Integer id, EnumEstadoVenta estado);
}
