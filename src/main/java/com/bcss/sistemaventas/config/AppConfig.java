package com.bcss.sistemaventas.config;

import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.service.TrabajadorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    private final TrabajadorService trabajadorService;

    public AppConfig(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Trabajador> trabajador = trabajadorService.findByEmail(username);
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(trabajador.get().getEmail())
                    .password(trabajador.get().getPassword())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
