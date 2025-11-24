package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.VentaDetails;
import com.bcss.sistemaventas.dto.request.VentaDetailsRequest;

import java.util.List;

public interface VentaDetailsService {
    List<VentaDetails> getAll();
    VentaDetails getById(Integer id);
    List<VentaDetails> getByVenta(Integer idVenta);
    VentaDetails save(VentaDetailsRequest ventaDetails);
    VentaDetails update(Integer id, VentaDetailsRequest ventaDetails);
    void delete(Integer id);
}
