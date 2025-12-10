package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.domain.EnumEstadoVenta;
import com.bcss.sistemaventas.dto.request.EstadoVentaRequest;
import com.bcss.sistemaventas.dto.request.VentaRequest;
import com.bcss.sistemaventas.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sell")
public class VentaController {
    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) EnumEstadoVenta estado) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VentaRequest venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(venta));
    }

    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VentaRequest venta) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, venta));
    }

    @PatchMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<?> changeStatus(@RequestBody EstadoVentaRequest estado, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id, estado.estado()));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getBySucursal(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBySucursal(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getByCliente(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByCliente(id));
    }

    @GetMapping("/trabajador/{id}")
    public ResponseEntity<?> getByTrabajador(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByTrabajador(id));
    }

    @GetMapping("/total-mes")
    public ResponseEntity<?> getTotalMes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTotalVentasMes());
    }
}
