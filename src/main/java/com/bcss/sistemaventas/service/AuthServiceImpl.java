package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.TrabajadorAuthRequest;
import com.bcss.sistemaventas.dto.request.TrabajadorRegisterRequest;
import com.bcss.sistemaventas.dto.response.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final TrabajadorService trabajadorService;
    private final PersonaService personaService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(TokenService tokenService, TrabajadorService trabajadorService, PersonaService personaService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.trabajadorService = trabajadorService;
        this.personaService = personaService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenResponse register(TrabajadorRegisterRequest trabajadorRegisterRequest) {
        return null;
    }

    @Override
    public TokenResponse login(TrabajadorAuthRequest trabajadorAuthRequest) {
        return null;
    }

    @Override
    public void saveUserToken(Trabajador trabajador, String token) {

    }

    @Override
    public void removeAllUserTokens(Trabajador trabajador) {

    }

    @Override
    public TokenResponse refreshToken(String authentication) {
        return null;
    }
}
