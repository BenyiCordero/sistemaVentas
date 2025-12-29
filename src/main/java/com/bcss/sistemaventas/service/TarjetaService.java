package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Tarjeta;
import com.bcss.sistemaventas.dto.request.TarjetaCreateRequest;

import java.util.List;

public interface TarjetaService {

    Tarjeta save(TarjetaCreateRequest request);
    List<Tarjeta> findAll();

}
