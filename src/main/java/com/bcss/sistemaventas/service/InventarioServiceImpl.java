package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Inventario;
import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.InventarioRepository;
import com.bcss.sistemaventas.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;
    private final SucursalRepository sucursalRepository;

    public InventarioServiceImpl(InventarioRepository repository, SucursalRepository sucursalRepository) {
        this.repository = repository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<Inventario> getAll() {
        List<Inventario> inventarios = repository.findAll();
        if(inventarios.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarios;
    }

    @Override
    public Inventario save(Inventario inventario) {
        Sucursal sucursal = sucursalRepository.findById(inventario.getSucursal().getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Inventario inventarioSaved = Inventario.builder()
                .titulo(inventario.getTitulo())
                .descripcion(inventario.getDescripcion())
                .fechaCreacion(LocalDate.now())
                .sucursal(sucursal)
                .build();
        return repository.save(inventarioSaved);
    }

    @Override
    public Inventario getInventarioPerSucursal(Integer id) {
        Optional<Inventario> inventario = repository.findBySucursal_idSucursal(id);
        if (inventario.isPresent()) return inventario.get();
        else throw new NotFoundException("Inventario con Sucursal ID: " + id + " no encontrado");
    }
}
