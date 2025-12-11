package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.TrabajadorUpdateRequest;
import com.bcss.sistemaventas.dto.response.TrabajadorDetailResponse;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador);
    Optional<Trabajador> findByEmail(String email);
    TrabajadorDetailResponse findByEmailDetailed(String email);
    List<TrabajadorDetailResponse> findAll();
    List<Trabajador> findAllTrabajadoresBySucursal(Integer id);
    Optional<Trabajador> findById(Integer idTrabajador);
    Trabajador updateTrabajador(Integer id, TrabajadorUpdateRequest trabajadorUpdateRequest);
}
