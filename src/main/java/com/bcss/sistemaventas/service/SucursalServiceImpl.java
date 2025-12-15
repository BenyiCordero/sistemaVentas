package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.SucursalPerUsuarioRequest;
import com.bcss.sistemaventas.dto.response.SucursalPerUsuarioResponse;
import com.bcss.sistemaventas.exception.EmptyObjectException;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.SucursalRepository;
import com.bcss.sistemaventas.repository.TrabajadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final TrabajadorRepository trabajadorRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository,
                               TrabajadorRepository trabajadorRepository) {
        this.sucursalRepository = sucursalRepository;
        this.trabajadorRepository = trabajadorRepository;
    }


    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal findById(Integer id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if(sucursal.isPresent()) return sucursal.get();
        else throw new NotFoundException("Sucursal no existente");
    }

    @Override
    public Sucursal findByName(String name) {
        Optional<Sucursal> sucursal = sucursalRepository.findByNombre(name);
        if(sucursal.isPresent()) return sucursal.get();
        else throw new NotFoundException(("Sucursal no encontrada"));
    }

    @Override
    public List<Sucursal> getAllSucursales() {
        List<Sucursal> sucursals = sucursalRepository.findAll();
        if (!sucursals.isEmpty()) return sucursals;
        else throw new EmptyObjectException("No hay sucursales para mostrar");
    }

    @Override
    public Sucursal updateSucursal(Sucursal sucursal) {
        Sucursal sucursalToReplace = sucursalRepository.findById(sucursal.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        BeanUtils.copyProperties(sucursal, sucursalToReplace, "id");
        return sucursalRepository.save(sucursalToReplace);
    }


    @Override
    public void deleteSucursalById(Integer id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if (sucursal.isPresent()) {
            Sucursal existingSucursal = sucursal.get();
            existingSucursal.setActivo(false);
            sucursalRepository.save(existingSucursal);
            return;
        }
        throw new NotFoundException("Sucursal no encontrada");
    }

    @Override
    public List<Sucursal> getAllActive() {
        List<Sucursal> sucursales = sucursalRepository.findByActivo(true);
        if (!sucursales.isEmpty()) return sucursales;
        else throw new EmptyObjectException("No hay sucursales para mostrar");
    }

    @Override
    public List<Sucursal> getAllInactive() {
        List<Sucursal> sucursales = sucursalRepository.findByActivo(false);
        if (!sucursales.isEmpty()) return sucursales;
        else throw new EmptyObjectException("No hay sucursales para mostrar");
    }

    @Override
    public SucursalPerUsuarioResponse getSucursalPerUsuario(SucursalPerUsuarioRequest request) {

        Trabajador trabajador = trabajadorRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        Sucursal s = trabajador.getSucursal();

        if (s == null) {
            throw new RuntimeException("El trabajador no tiene sucursal");
        }

        return new SucursalPerUsuarioResponse(
                s.getIdSucursal()
        );
    }



}
