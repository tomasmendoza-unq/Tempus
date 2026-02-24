package edu.ar.tempus.controller.dto.usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequestDTO(
    String email,
    String password
){
    public UsernamePasswordAuthenticationToken aModelo(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
