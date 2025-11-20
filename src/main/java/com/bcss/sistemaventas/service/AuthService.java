package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.TrabajadorAuthRequest;
import com.bcss.sistemaventas.dto.request.TrabajadorRegisterRequest;
import com.bcss.sistemaventas.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse register(TrabajadorRegisterRequest trabajadorRegisterRequest);
    TokenResponse login(TrabajadorAuthRequest trabajadorAuthRequest);
    void saveUserToken(Trabajador trabajador, String token);
    void removeAllUserTokens(Trabajador trabajador);
    TokenResponse refreshToken(String authentication);
}
