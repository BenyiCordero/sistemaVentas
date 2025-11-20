package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador);
    Optional<Trabajador> findByEmail(String email);
    List<Trabajador> findAllTrabajadoresBySucursal(Integer id);
    Optional<Trabajador> findById(Integer idTrabajador);
}
