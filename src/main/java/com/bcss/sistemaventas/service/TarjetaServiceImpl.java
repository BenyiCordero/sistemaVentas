package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Tarjeta;
import com.bcss.sistemaventas.dto.request.TarjetaCreateRequest;
import com.bcss.sistemaventas.repository.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public Tarjeta save(TarjetaCreateRequest request) {
        return tarjetaRepository.save(Tarjeta.builder()
                        .nombreTarjeta(request.nombreTarjeta())
                        .tipoTarjeta(request.tipoTarjeta())
                        .numeroTarjeta(request.numeroTarjeta())
                .build());
    }

    @Override
    public List<Tarjeta> findAll() {
        return tarjetaRepository.findAll();
    }
}
