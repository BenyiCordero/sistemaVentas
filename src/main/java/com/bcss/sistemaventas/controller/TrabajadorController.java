package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.TrabajadorUpdateRequest;
import com.bcss.sistemaventas.service.TrabajadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        var trabajador = trabajadorService.findByEmail(email);
        if (trabajador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado con email: " + email);
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabajador.get());
    }

    @GetMapping("/getByEmailDetailed")
    public ResponseEntity<?> getByEmailDetailed(@RequestParam String email) {
        var trabajador = trabajadorService.findByEmailDetailed(email);
        if(trabajador == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabajador);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrabajador(@PathVariable Integer id,@RequestBody TrabajadorUpdateRequest trabajadorUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.updateTrabajador(id, trabajadorUpdateRequest));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findAll());
    }

}
