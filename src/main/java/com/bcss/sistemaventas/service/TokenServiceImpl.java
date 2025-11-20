package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Token;
import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String extractUsername(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public String generateToken(Trabajador trabajador) {
        return buildToken(trabajador, jwtExpiration);
    }

    @Override
    public String generateRefreshToken(Trabajador trabajador) {
        return buildToken(trabajador, refreshExpiration);
    }

    @Override
    public String buildToken(Trabajador trabajador, Long expiration) {
        return Jwts
                .builder()
                .claims(Map.of("name", trabajador.getPersona().getNombre()))
                .subject(trabajador.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, Trabajador trabajador) {
        String username = extractUsername(token);
        return (username.equals(trabajador.getEmail()) && !isTokenExpired(token));
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Date extractExpiration(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    @Override
    public SecretKey getSignInKey() {
        final byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public List<Token> findAllTokensByTrabajador(Trabajador trabajador) {
        return tokenRepository.findByTrabajador(trabajador);
    }

    @Override
    public void deleteTokens(List<Token> tokens) {
        tokenRepository.deleteAll(tokens);
    }

}
