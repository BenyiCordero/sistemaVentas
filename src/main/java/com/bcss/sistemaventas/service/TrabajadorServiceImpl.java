package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import com.bcss.sistemaventas.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajadorRepository.existsByEmail(trabajador.getEmail())) throw new RepeatedException("Trabajador ya existente");
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Optional<Trabajador> findByEmail(String email) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByEmail(email);
        if(trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public Optional<Trabajador> findById(Integer idTrabajador) {
        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);
        if (trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public List<Trabajador> findAllTrabajadoresBySucursal(Integer id) {
        return null;
    }

}
