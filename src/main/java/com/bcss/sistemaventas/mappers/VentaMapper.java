package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Venta;
import com.bcss.sistemaventas.dto.response.VentaBasicResponse;
import com.bcss.sistemaventas.dto.response.VentaDetailResponse;
import com.bcss.sistemaventas.dto.response.VentaResponse;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public VentaBasicResponse mapToBasic(Venta venta) {
        var persona = venta.getCliente().getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        var trabajadorPersona = venta.getTrabajador().getPersona();
        String trabajadorNombre = trabajadorPersona.getNombre() + " " + 
                                (trabajadorPersona.getPrimerApellido() != null ? trabajadorPersona.getPrimerApellido() : "");
        
        return new VentaBasicResponse(
            venta.getIdVenta(),
            venta.getFechaVenta(),
            venta.getTotalVenta(),
            venta.getEstado().name(),
            nombreCompleto.trim(),
            trabajadorNombre.trim(),
            venta.getSucursal().getNombre()
        );
    }

    public VentaDetailResponse mapToDetail(Venta venta) {
        var persona = venta.getCliente().getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        var trabajadorPersona = venta.getTrabajador().getPersona();
        String trabajadorNombre = trabajadorPersona.getNombre() + " " + 
                                (trabajadorPersona.getPrimerApellido() != null ? trabajadorPersona.getPrimerApellido() : "");
        
        return new VentaDetailResponse(
            venta.getIdVenta(),
            venta.getFechaVenta(),
            venta.getTotalVenta(),
            venta.getDescuento(),
            venta.getImpuesto(),
            venta.getEstado().name(),
            venta.getNotas(),
            venta.getCliente().getIdCliente(),
            nombreCompleto.trim(),
            venta.getTrabajador().getIdUsuario(),
            trabajadorNombre.trim(),
            venta.getSucursal().getIdSucursal(),
            venta.getSucursal().getNombre()
        );
    }

    public VentaResponse mapToResponse(Venta venta) {
        var persona = venta.getCliente().getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        var trabajadorPersona = venta.getTrabajador().getPersona();
        String trabajadorNombre = trabajadorPersona.getNombre() + " " + 
                                (trabajadorPersona.getPrimerApellido() != null ? trabajadorPersona.getPrimerApellido() : "");
        
        return new VentaResponse(
            venta.getIdVenta(),
            venta.getFechaVenta(),
            venta.getTotalVenta(),
            venta.getDescuento(),
            venta.getImpuesto(),
            venta.getEstado().name(),
            venta.getNotas(),
            venta.getCliente().getIdCliente(),
            nombreCompleto.trim(),
            venta.getTrabajador().getIdUsuario(),
            trabajadorNombre.trim(),
            venta.getSucursal().getIdSucursal(),
            venta.getSucursal().getNombre()
        );
    }
}