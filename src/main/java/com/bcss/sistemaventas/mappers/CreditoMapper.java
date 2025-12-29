package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Credito;
import com.bcss.sistemaventas.dto.response.CreditoResponse;
import com.bcss.sistemaventas.dto.response.CreditoDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {

    public CreditoResponse mapToResponse(Credito credito) {
        return new CreditoResponse(
            credito.getIdCredito(),
            credito.getVenta() != null ? credito.getVenta().getIdVenta() : null,
            credito.getMontoInicial(),
            credito.getSaldo(),
            credito.getTasaInteres(),
            credito.getPlazoMeses(),
            credito.getEstado().name(),
            credito.getFechaInicio(),
            credito.getFechaVencimiento()
        );
    }

    public CreditoDetailResponse mapToDetail(Credito credito) {
        return new CreditoDetailResponse(
            credito.getIdCredito(),
            credito.getVenta() != null ? credito.getVenta().getIdVenta() : null,
            credito.getMontoInicial(),
            credito.getSaldo(),
            credito.getTasaInteres(),
            credito.getPlazoMeses(),
            credito.getEstado().name(),
            credito.getFechaInicio(),
            credito.getFechaVencimiento()
        );
    }
}