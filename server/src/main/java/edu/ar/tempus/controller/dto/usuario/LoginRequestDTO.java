package com.ecovida.ecommerce_backend.controller.dto.usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequestDTO(
    String email,
    String password
){
    public UsernamePasswordAuthenticationToken aModelo(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
