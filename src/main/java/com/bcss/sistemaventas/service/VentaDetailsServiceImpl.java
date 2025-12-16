package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Producto;
import com.bcss.sistemaventas.domain.Venta;
import com.bcss.sistemaventas.domain.VentaDetails;
import com.bcss.sistemaventas.dto.request.VentaDetailsRequest;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.ProductoRepository;
import com.bcss.sistemaventas.repository.VentaDetailsRepository;
import com.bcss.sistemaventas.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaDetailsServiceImpl implements VentaDetailsService {

    private final VentaDetailsRepository repository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaDetailsServiceImpl(VentaDetailsRepository repository, VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.repository = repository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<VentaDetails> getAll() {
        List<VentaDetails> ventaDetails = repository.findAll();
        if (ventaDetails.isEmpty()) {
            throw new NotFoundException("No hay registros de ventaDetails");
        }
        return ventaDetails;
    }

    @Override
    public VentaDetails getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("VentaDetails no encontrada con id: " + id));
    }

    @Override
    public List<VentaDetails> getByVenta(Integer idVenta) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con id: " + idVenta));

        List<VentaDetails> ventaDetails = repository.findByVenta(venta);
        if (ventaDetails.isEmpty()) throw new NotFoundException("No hay registros de VentaDetails para la venta " + idVenta);
        else return ventaDetails;
    }

    @Override
    public VentaDetails save(VentaDetailsRequest ventaDetailsRequest) {
        Producto producto = productoRepository.findById(ventaDetailsRequest.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + ventaDetailsRequest.idProducto()));

        Venta venta = ventaRepository.findById(ventaDetailsRequest.idVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + ventaDetailsRequest.idVenta()));

        VentaDetails ventaDetails = VentaDetails.builder()
                .producto(producto)
                .venta(venta)
                .cantidad(ventaDetailsRequest.cantidad())
                .precio(ventaDetailsRequest.precio())
                .subtotal(ventaDetailsRequest.subtotal())
                .build();

        return repository.save(ventaDetails);
    }

    @Override
    public VentaDetails update(Integer id, VentaDetailsRequest ventaDetailsRequest) {
        VentaDetails existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detalle no encontrado con id: " + id));

        Producto producto = productoRepository.findById(ventaDetailsRequest.idProducto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + ventaDetailsRequest.idProducto()));

        Venta venta = ventaRepository.findById(ventaDetailsRequest.idVenta())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con id: " + ventaDetailsRequest.idVenta()));

        // actualizar el objeto existente
        existing.setProducto(producto);
        existing.setVenta(venta);
        existing.setCantidad(ventaDetailsRequest.cantidad());
        existing.setPrecio(ventaDetailsRequest.precio());
        existing.setSubtotal(ventaDetailsRequest.subtotal());

        return repository.save(existing);
    }


    @Override
    public void delete(Integer id) {
        VentaDetails existing = getById(id);
        repository.delete(existing);
    }

}
