package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.GastoSaveRequest;
import com.bcss.sistemaventas.dto.response.GastoResponse;
import com.bcss.sistemaventas.service.GastoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/gasto")
public class GastoController {

    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping
    public ResponseEntity<GastoResponse> create(
            @RequestBody GastoSaveRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(gastoService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoResponse> update(
            @PathVariable Integer id,
            @RequestBody GastoSaveRequest request
    ) {
        return ResponseEntity.ok(gastoService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(gastoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GastoResponse>> findAll() {
        return ResponseEntity.ok(gastoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        gastoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total/mes/{idSucursal}")
    public Float totalMes(@PathVariable Integer idSucursal) {
        return gastoService.totalMesActualPorSucursal(idSucursal);
    }

    @GetMapping("/total/hoy/{idSucursal}")
    public Float totalHoy(@PathVariable Integer idSucursal) {
        return gastoService.totalHoyPorSucursal(idSucursal);
    }

    @GetMapping("/total/semana/{idSucursal}")
    public Float totalSemana(@PathVariable Integer idSucursal) {
        return gastoService.totalSemanaActualPorSucursal(idSucursal);
    }

    @GetMapping("/promedio/diario/{idSucursal}")
    public Float promedioDiario(@PathVariable Integer idSucursal) {
        return gastoService.promedioDiarioMesActual(idSucursal);
    }

    @GetMapping("/total/rango")
    public Float totalPorRango(
            @RequestParam Integer idSucursal,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fechaInicio,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fechaFin
    ) {
        return gastoService.totalPorRangoFechas(
                idSucursal,
                fechaInicio,
                fechaFin
        );
    }
}
