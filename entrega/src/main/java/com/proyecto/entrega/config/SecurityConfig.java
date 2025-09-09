package com.proyecto.entrega.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Define cómo se codificarán las contraseñas y deshabilita la seguridad web por defecto
@Configuration
public class SecurityConfig {

    @Bean // Bean para codificar contraseñas. BCrypt es un algoritmo "hashea" la contraseña
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Bean que define el filtro principal de seguridad web.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
// PasswordEncoder se usa en el UsuarioService (cuando guardas un usuario, la contraseña se guarda hasheada con BCrypt).
// SecurityFilterChain define que, por ahora:
// No hay login ni roles obligatorios.
// Todas las rutas de los controladores (/api/...) están accesibles sin autenticación.