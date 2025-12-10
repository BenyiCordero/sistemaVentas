package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Cliente;
import com.bcss.sistemaventas.dto.response.ClienteBasicResponse;
import com.bcss.sistemaventas.dto.response.ClienteDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteBasicResponse mapToBasic(Cliente cliente) {
        var persona = cliente.getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new ClienteBasicResponse(
            cliente.getIdCliente(),
            nombreCompleto.trim(),
            persona.getNumeroTelefono(),
            cliente.getCreditoActivo()
        );
    }

    public ClienteDetailResponse mapToDetail(Cliente cliente) {
        var persona = cliente.getPersona();
        
        return new ClienteDetailResponse(
            cliente.getIdCliente(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getNumeroTelefono(),
            cliente.getCreditoActivo()
        );
    }
}