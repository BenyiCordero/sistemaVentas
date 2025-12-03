package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Rol;
import com.bcss.sistemaventas.domain.TokenType;
import com.bcss.sistemaventas.domain.Persona;
import com.bcss.sistemaventas.domain.Token;
import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.TrabajadorAuthRequest;
import com.bcss.sistemaventas.dto.request.TrabajadorRegisterRequest;
import com.bcss.sistemaventas.dto.response.TokenResponse;
import com.bcss.sistemaventas.exception.InvalidEntryException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public TokenResponse register(TrabajadorRegisterRequest trabajadorRegisterRequest) {
        Persona persona = Persona
                .builder()
                .nombre(trabajadorRegisterRequest.nombre())
                .primerApellido(trabajadorRegisterRequest.primerApellido())
                .segundoApellido(trabajadorRegisterRequest.segundoApellido())
                .numeroTelefono(trabajadorRegisterRequest.numeroTelefono())
                .build();
        Persona personaSaved = personaService.save(persona);
        Trabajador trabajador = Trabajador
                .builder()
                .email(trabajadorRegisterRequest.email())
                .password(passwordEncoder.encode(trabajadorRegisterRequest.password()))
                .rol(Rol.ROLE_ADMIN)
                .persona(personaSaved)
                .build();
        trabajadorService.save(trabajador);
        String token = tokenService.generateToken(trabajador);
        String refreshToken = tokenService.generateRefreshToken(trabajador);

        saveUserToken(trabajador, token);
        return new TokenResponse(token, refreshToken);
    }

    @Transactional
    public TokenResponse login(TrabajadorAuthRequest trabajadorAuthRequest) {
        try{
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    trabajadorAuthRequest.email(),
                                    trabajadorAuthRequest.password()
                            )
                    );
        } catch (InvalidEntryException ex){
            throw new InvalidEntryException("Credenciales invalidas");
        }

        Optional<Trabajador> trabajador = trabajadorService.findByEmail(trabajadorAuthRequest.email());
        String accessToken = tokenService.generateToken(trabajador.get());
        String refreshToken = tokenService.generateRefreshToken(trabajador.get());
        removeAllUserTokens(trabajador.get());
        saveUserToken(trabajador.get(), accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public void saveUserToken(Trabajador trabajador, String token) {
        Token tokenJwt = Token
                .builder()
                .token(token)
                .isExpired(false)
                .isRevoked(false)
                .type(TokenType.BEARER)
                .trabajador(trabajador)
                .build();
        tokenService.saveToken(tokenJwt);
    }

    @Transactional
    public void removeAllUserTokens(Trabajador trabajador) {
        List<Token> tokens = tokenService.findAllTokensByTrabajador(trabajador);
        if(!tokens.isEmpty()){
            tokenService.deleteTokens(tokens);
        }
    }

    @Transactional
    public TokenResponse refreshToken(String authentication) {
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new InvalidEntryException("Error de entrada de token");
        }
        String refreshToken = authentication.substring(7);
        String email = tokenService.extractUsername(refreshToken);
        if (email == null) {
            return null;
        }

        Trabajador trabajador = trabajadorService.findByEmail(email).orElseThrow();
        boolean isTokenValid = tokenService.isTokenValid(refreshToken, trabajador);
        if (!isTokenValid) {
            return null;
        }

        String accessToken = tokenService.generateRefreshToken(trabajador);
        removeAllUserTokens(trabajador);
        saveUserToken(trabajador, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
