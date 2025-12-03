package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Credito;
import com.bcss.sistemaventas.dto.response.CreditoResponse;
import com.bcss.sistemaventas.dto.response.CreditoDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {

    public CreditoResponse mapToResponse(Credito credito) {
        var persona = credito.getCliente().getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new CreditoResponse(
            credito.getIdCredito(),
            credito.getCliente().getIdCliente(),
            nombreCompleto.trim(),
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
        var persona = credito.getCliente().getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new CreditoDetailResponse(
            credito.getIdCredito(),
            credito.getCliente().getIdCliente(),
            nombreCompleto.trim(),
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