package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.auth.LoginRequestDTO;
import edu.ar.tempus.controller.dto.auth.LoginResponseDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioRequestDTO;
import edu.ar.tempus.model.usuario.Usuario;
import edu.ar.tempus.service.AuthService;
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
    public ResponseEntity<LoginResponseDTO> register(
            @RequestBody @Valid UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = authService.registrarUsuario(usuarioDTO.aModelo(), usuarioDTO.carreraId());


        LoginResponseDTO response = authService.generarRespuestaPostRegistro(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequest) {

        LoginResponseDTO response = authService.autenticarUsuario(loginRequest.aModelo());
        return ResponseEntity.ok(response);
    }
}