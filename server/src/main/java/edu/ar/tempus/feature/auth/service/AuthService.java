package edu.ar.tempus.feature.auth.service;

import edu.ar.tempus.controller.dto.auth.LoginResponseDTO;
import edu.ar.tempus.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    Usuario registrar(Usuario usuario, Long carreraId);

    LoginResponseDTO autenticarUsuario(UsernamePasswordAuthenticationToken loginRequest);

    LoginResponseDTO generarRespuestaPostRegistro(Usuario usuario);
}
