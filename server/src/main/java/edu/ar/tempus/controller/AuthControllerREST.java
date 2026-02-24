package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.usuario.LoginRequestDTO;
import edu.ar.tempus.controller.dto.usuario.LoginResponseDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioRequestDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDTO;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public final class AuthControllerREST {

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