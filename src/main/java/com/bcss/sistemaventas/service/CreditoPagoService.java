package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.dto.response.CreditoPagoResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoListResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoDetailResponse;
import com.bcss.sistemaventas.dto.request.CreditoPagoRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface CreditoPagoService {
    
    CreditoPagoResponse crearCreditoPago(CreditoPagoRequest request);
    
    CreditoPagoResponse obtenerCreditoPagoPorId(Integer id);
    
    CreditoPagoDetailResponse obtenerDetalleCreditoPagoPorId(Integer id);
    
    CreditoPagoListResponse listarCreditoPagos();
    
    CreditoPagoListResponse listarCreditoPagosPorCredito(Integer idCredito);
    
    CreditoPagoListResponse listarCreditoPagosPorPago(Integer idPago);
    
    List<CreditoPagoResponse> listarCreditoPagosPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    List<CreditoPagoResponse> listarCreditoPagosPorCreditoOrdenadosPorFecha(Integer idCredito);
    
    void eliminarCreditoPago(Integer id);
    
    boolean existeCreditoPago(Integer idCredito, Integer idPago);
}