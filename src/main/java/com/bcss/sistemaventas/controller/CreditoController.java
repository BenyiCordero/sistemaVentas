package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.response.CreditoResponse;
import com.bcss.sistemaventas.dto.response.CreditoListResponse;
import com.bcss.sistemaventas.dto.request.CreditoRequest;
import com.bcss.sistemaventas.dto.request.EstadoCreditoRequest;
import com.bcss.sistemaventas.service.CreditoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credito")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService creditoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CreditoResponse> crearCredito(@Valid @RequestBody CreditoRequest request) {
        CreditoResponse response = creditoService.crearCredito(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{idCredito}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoResponse> obtenerCreditoPorId(@PathVariable Integer idCredito) {
        CreditoResponse response = creditoService.obtenerCreditoPorId(idCredito);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CreditoListResponse> listarCreditos() {
        CreditoListResponse response = creditoService.listarCreditos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoListResponse> listarCreditosPorCliente(@PathVariable Integer idCliente) {
        CreditoListResponse response = creditoService.listarCreditosPorCliente(idCliente);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CreditoListResponse> listarCreditosPorEstado(@PathVariable String estado) {
        CreditoListResponse response = creditoService.listarCreditosPorEstado(estado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vencidos")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<List<CreditoResponse>> obtenerCreditosVencidos() {
        List<CreditoResponse> response = creditoService.obtenerCreditosVencidos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idCredito}/estado")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CreditoResponse> actualizarEstadoCredito(
            @PathVariable Integer idCredito,
            @Valid @RequestBody EstadoCreditoRequest request) {
        CreditoResponse response = creditoService.actualizarEstadoCredito(idCredito, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idCredito}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCredito(@PathVariable Integer idCredito) {
        creditoService.eliminarCredito(idCredito);
        return ResponseEntity.noContent().build();
    }
}