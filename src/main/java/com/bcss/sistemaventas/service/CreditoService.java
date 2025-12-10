package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.dto.response.CreditoResponse;
import com.bcss.sistemaventas.dto.response.CreditoListResponse;
import com.bcss.sistemaventas.dto.request.CreditoRequest;
import com.bcss.sistemaventas.dto.request.EstadoCreditoRequest;

import java.util.List;

public interface CreditoService {
    
    CreditoResponse crearCredito(CreditoRequest request);
    
    CreditoResponse obtenerCreditoPorId(Integer idCredito);
    
    CreditoListResponse listarCreditos();
    
    CreditoListResponse listarCreditosPorCliente(Integer idCliente);
    
    CreditoListResponse listarCreditosPorEstado(String estado);
    
    CreditoResponse actualizarEstadoCredito(Integer idCredito, EstadoCreditoRequest request);
    
    List<CreditoResponse> obtenerCreditosVencidos();
    
    void eliminarCredito(Integer idCredito);
}