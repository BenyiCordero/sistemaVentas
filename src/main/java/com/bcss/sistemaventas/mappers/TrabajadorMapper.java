package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.response.TrabajadorBasicResponse;
import com.bcss.sistemaventas.dto.response.TrabajadorDetailResponse;
import com.bcss.sistemaventas.dto.response.TrabajadorResponse;
import org.springframework.stereotype.Component;

@Component
public class TrabajadorMapper {

    public TrabajadorBasicResponse mapToBasic(Trabajador trabajador) {
        var persona = trabajador.getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new TrabajadorBasicResponse(
            trabajador.getIdUsuario(),
            trabajador.getEmail(),
            trabajador.getRol().name(),
            nombreCompleto.trim(),
            trabajador.getSucursal() != null ? trabajador.getSucursal().getNombre() : null
        );
    }

    public TrabajadorDetailResponse mapToDetail(Trabajador trabajador) {
        var persona = trabajador.getPersona();
        
        return new TrabajadorDetailResponse(
            trabajador.getIdUsuario(),
            trabajador.getEmail(),
            trabajador.getRol().name(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getNumeroTelefono(),
            trabajador.getSucursal() != null ? trabajador.getSucursal().getIdSucursal() : null,
            trabajador.getSucursal() != null ? trabajador.getSucursal().getNombre() : null
        );
    }

    public TrabajadorResponse mapToResponse(Trabajador trabajador) {
        var persona = trabajador.getPersona();
        
        return new TrabajadorResponse(
            trabajador.getIdUsuario(),
            trabajador.getEmail(),
            trabajador.getRol().name(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getNumeroTelefono(),
            trabajador.getSucursal() != null ? trabajador.getSucursal().getIdSucursal() : null,
            trabajador.getSucursal() != null ? trabajador.getSucursal().getNombre() : null
        );
    }
}