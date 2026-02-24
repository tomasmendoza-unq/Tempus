package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.usuario.LoginResponseDTO;
import edu.ar.tempus.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    Usuario registrarUsuario(Usuario usuario);

    LoginResponseDTO autenticarUsuario(UsernamePasswordAuthenticationToken loginRequest);
}
