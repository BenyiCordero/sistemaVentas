package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.service.TrabajadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findById(id));
    }

}
