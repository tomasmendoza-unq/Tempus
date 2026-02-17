package com.ecovida.ecommerce_backend.controller;

import com.ecovida.ecommerce_backend.controller.dto.usuario.*;
import com.ecovida.ecommerce_backend.models.Usuario;
import com.ecovida.ecommerce_backend.services.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllerREST {

    private final AuthService authService;

    public AuthControllerREST(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(
            @RequestBody @Valid UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = authService.registrarUsuario(usuarioDTO.aModelo());

        UsuarioResponseDTO response = UsuarioResponseDTO.desdeModelo(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequest) {

        LoginResponseDTO response = authService.autenticarUsuario(loginRequest.aModelo());
        return ResponseEntity.ok(response);
    }
}