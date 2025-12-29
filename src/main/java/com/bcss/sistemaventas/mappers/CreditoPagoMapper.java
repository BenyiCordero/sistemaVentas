package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.CreditoPago;
import com.bcss.sistemaventas.dto.response.CreditoPagoResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoBasicResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoDetailResponse;
import com.bcss.sistemaventas.dto.response.CreditoBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditoPagoMapper {

    public CreditoPagoResponse toResponse(CreditoPago creditoPago) {
        if (creditoPago == null) {
            return null;
        }

        Integer idPago = null;
        if (creditoPago.getPago() != null) {
            idPago = creditoPago.getPago().getIdPago();
        }

        return new CreditoPagoResponse(
                creditoPago.getId(),
                creditoPago.getCredito().getIdCredito(),
                idPago,
                creditoPago.getMonto(),
                creditoPago.getFecha(),
                creditoPago.getCreatedAt()
        );
    }

    public CreditoPagoBasicResponse toBasicResponse(CreditoPago creditoPago) {
        if (creditoPago == null) {
            return null;
        }

return new CreditoPagoBasicResponse(
                creditoPago.getId(),
                creditoPago.getCredito().getIdCredito(),
                creditoPago.getMonto(),
                creditoPago.getFecha()
        );
    }

    public CreditoPagoDetailResponse toDetailResponse(CreditoPago creditoPago) {
        if (creditoPago == null) {
            return null;
        }

        Integer idPago = null;
        if (creditoPago.getPago() != null) {
            idPago = creditoPago.getPago().getIdPago();
        }

        CreditoBasicResponse creditoBasicResponse = null;
        if (creditoPago.getCredito() != null) {
            creditoBasicResponse = new CreditoBasicResponse(
                    creditoPago.getCredito().getIdCredito(),
                    creditoPago.getCredito().getMontoInicial(),
                    creditoPago.getCredito().getSaldo(),
                    creditoPago.getCredito().getTasaInteres(),
                    creditoPago.getCredito().getPlazoMeses(),
                    creditoPago.getCredito().getEstado() != null ? 
                            creditoPago.getCredito().getEstado().toString() : null,
                    creditoPago.getCredito().getFechaInicio(),
                    creditoPago.getCredito().getFechaVencimiento()
            );
        }

        return new CreditoPagoDetailResponse(
                creditoPago.getId(),
                creditoPago.getCredito().getIdCredito(),
                idPago,
                creditoPago.getMonto(),
                creditoPago.getFecha(),
                creditoPago.getCreatedAt(),
                null, // updatedAt - no existe en CreditoPago
                creditoBasicResponse
        );
    }
}