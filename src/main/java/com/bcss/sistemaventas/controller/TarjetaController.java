package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.domain.Tarjeta;
import com.bcss.sistemaventas.dto.request.TarjetaCreateRequest;
import com.bcss.sistemaventas.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarjeta")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping
    ResponseEntity<?> crearTarjeta(@RequestBody TarjetaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.save(request));
    }

    @GetMapping
    ResponseEntity<?> getTarjetas() {
        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.findAll());
    }
}
