package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.TrabajadorAuthRequest;
import com.bcss.sistemaventas.dto.request.TrabajadorRegisterRequest;
import com.bcss.sistemaventas.dto.response.TokenResponse;
import com.bcss.sistemaventas.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TrabajadorRegisterRequest trabajadorRegisterRequest) {
        TokenResponse tokenResponse = authService.register(trabajadorRegisterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TrabajadorAuthRequest trabajadorAuthRequest) {
        TokenResponse tokenResponse = authService.login(trabajadorAuthRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(token));
    }

}
