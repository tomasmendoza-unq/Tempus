package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.auth.LoginResponseDTO;
import edu.ar.tempus.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    Usuario registrarUsuario(Usuario usuario);

    LoginResponseDTO autenticarUsuario(UsernamePasswordAuthenticationToken loginRequest);

    LoginResponseDTO generarRespuestaPostRegistro(Usuario usuario);
}
