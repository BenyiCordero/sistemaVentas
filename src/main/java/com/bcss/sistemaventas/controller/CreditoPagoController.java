package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.response.CreditoPagoResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoListResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoDetailResponse;
import com.bcss.sistemaventas.dto.request.CreditoPagoRequest;
import com.bcss.sistemaventas.service.CreditoPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/credito-pagos")
@RequiredArgsConstructor
public class CreditoPagoController {

    private final CreditoPagoService creditoPagoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoPagoResponse> crearCreditoPago(@Valid @RequestBody CreditoPagoRequest request) {
        CreditoPagoResponse response = creditoPagoService.crearCreditoPago(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoPagoResponse> obtenerCreditoPagoPorId(@PathVariable Integer id) {
        CreditoPagoResponse response = creditoPagoService.obtenerCreditoPagoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detalle")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoPagoDetailResponse> obtenerDetalleCreditoPagoPorId(@PathVariable Integer id) {
        CreditoPagoDetailResponse response = creditoPagoService.obtenerDetalleCreditoPagoPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CreditoPagoListResponse> listarCreditoPagos() {
        CreditoPagoListResponse response = creditoPagoService.listarCreditoPagos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/credito/{idCredito}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoPagoListResponse> listarCreditoPagosPorCredito(@PathVariable Integer idCredito) {
        CreditoPagoListResponse response = creditoPagoService.listarCreditoPagosPorCredito(idCredito);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pago/{idPago}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<CreditoPagoListResponse> listarCreditoPagosPorPago(@PathVariable Integer idPago) {
        CreditoPagoListResponse response = creditoPagoService.listarCreditoPagosPorPago(idPago);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fechas")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<List<CreditoPagoResponse>> listarCreditoPagosPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        List<CreditoPagoResponse> response = creditoPagoService.listarCreditoPagosPorFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/credito/{idCredito}/ordenados")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<List<CreditoPagoResponse>> listarCreditoPagosPorCreditoOrdenadosPorFecha(@PathVariable Integer idCredito) {
        List<CreditoPagoResponse> response = creditoPagoService.listarCreditoPagosPorCreditoOrdenadosPorFecha(idCredito);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/existe")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<Boolean> existeCreditoPago(
            @RequestParam Integer idCredito,
            @RequestParam(required = false) Integer idPago) {
        boolean existe = creditoPagoService.existeCreditoPago(idCredito, idPago);
        return ResponseEntity.ok(existe);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCreditoPago(@PathVariable Integer id) {
        creditoPagoService.eliminarCreditoPago(id);
        return ResponseEntity.noContent().build();
    }
}