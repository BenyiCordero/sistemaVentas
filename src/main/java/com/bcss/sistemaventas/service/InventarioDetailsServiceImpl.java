package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Inventario;
import com.bcss.sistemaventas.domain.InventarioDetails;
import com.bcss.sistemaventas.domain.Producto;
import com.bcss.sistemaventas.dto.request.InventarioDetailsRequest;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.InventarioDetailsRepository;
import com.bcss.sistemaventas.repository.InventarioRepository;
import com.bcss.sistemaventas.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventarioDetailsServiceImpl implements InventarioDetailsService {

    private final InventarioDetailsRepository repository;
    private final ProductoRepository productoRepository;
    private final InventarioRepository inventarioRepository;

    public InventarioDetailsServiceImpl(InventarioDetailsRepository repository,
                                        ProductoRepository productoRepository,
                                        InventarioRepository inventarioRepository) {
        this.repository = repository;
        this.productoRepository = productoRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public List<InventarioDetails> getAll() {
        List<InventarioDetails> inventarioDetailsList = repository.findAll();
        if (inventarioDetailsList.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarioDetailsList;
    }

    @Override
    public List<InventarioDetails> getAllDisponibles() {
        List<InventarioDetails> inventarioDetailsList = repository.findByDisponible(true);
        if (inventarioDetailsList.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarioDetailsList;
    }

    @Override
    public List<InventarioDetails> getAllNoDisponibles() {
        List<InventarioDetails> inventarioDetailsList = repository.findByDisponible(false);
        if (inventarioDetailsList.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarioDetailsList;
    }

    @Override
    public InventarioDetails getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay un detalle con id " + id));
    }

    @Override
    public InventarioDetails save(InventarioDetailsRequest inventaioDetails) {
        Inventario inventario = inventarioRepository.findById(inventaioDetails.idInventario())
                .orElseThrow(() -> new NotFoundException("No se encontró Inventario con id " + inventaioDetails.idInventario()));

        Producto producto = productoRepository.findById(inventaioDetails.idProducto())
                .orElseThrow(() -> new NotFoundException("No se encontró Producto con id " + inventaioDetails.idProducto()));

        InventarioDetails detalle = InventarioDetails.builder()
                .cantidad(inventaioDetails.cantidad())
                .estado(inventaioDetails.estado())
                .disponible(inventaioDetails.disponible())
                .fechaAgregado(LocalDate.now())
                .inventario(inventario)
                .producto(producto)
                .build();

        return repository.save(detalle);
    }

    @Override
    public InventarioDetails update(Integer id, InventarioDetailsRequest inventarioDetails) {
        InventarioDetails detalle = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró detalle con id " + id));

        Inventario inventario = inventarioRepository.findById(inventarioDetails.idInventario())
                .orElseThrow(() -> new NotFoundException("No se encontró Inventario con id " + inventarioDetails.idInventario()));

        Producto producto = productoRepository.findById(inventarioDetails.idProducto())
                .orElseThrow(() -> new NotFoundException("No se encontró Producto con id " + inventarioDetails.idProducto()));

        detalle.setCantidad(inventarioDetails.cantidad());
        detalle.setEstado(inventarioDetails.estado());
        detalle.setDisponible(inventarioDetails.disponible());
        detalle.setInventario(inventario);
        detalle.setProducto(producto);

        return repository.save(detalle);
    }

    @Override
    public void delete(Integer id) {
        InventarioDetails detalle = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró detalle con id " + id));

        detalle.setDisponible(false);

        repository.save(detalle);
    }

    @Override
    public List<InventarioDetails> getByInventario(Integer idInventario) {
        if (!inventarioRepository.findById(idInventario).isPresent()) throw new NotFoundException("Inventario no encontrado");
        else return repository.findByInventarioIdInventarioAndDisponibleTrue(idInventario);
    }

    @Override
    public List<InventarioDetails> getByProducto(Integer idProducto) {
        if (!productoRepository.findById(idProducto).isPresent()) throw new NotFoundException("Producto no encontrado");
        else return repository.findByProductoIdProductoAndDisponibleTrue(idProducto);
    }

}
