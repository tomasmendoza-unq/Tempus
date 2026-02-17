package com.ecovida.ecommerce_backend.services.interfaces;

import com.ecovida.ecommerce_backend.controller.dto.usuario.LoginRequestDTO;
import com.ecovida.ecommerce_backend.controller.dto.usuario.LoginResponseDTO;
import com.ecovida.ecommerce_backend.controller.dto.usuario.UsuarioRequestDTO;
import com.ecovida.ecommerce_backend.controller.dto.usuario.UsuarioResponseDTO;
import com.ecovida.ecommerce_backend.models.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    Usuario registrarUsuario(Usuario usuario);

    LoginResponseDTO autenticarUsuario(UsernamePasswordAuthenticationToken loginRequest);
}
