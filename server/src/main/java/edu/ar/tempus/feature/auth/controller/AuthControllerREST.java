package edu.ar.tempus.feature.auth.controller;

import edu.ar.tempus.controller.dto.auth.LoginRequestDTO;
import edu.ar.tempus.controller.dto.auth.LoginResponseDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioRequestDTO;
import edu.ar.tempus.controller.exceptions.ErrorResponseDTO;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.feature.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Registro de usuario")
    @ApiResponse(
            responseCode = "200",
            description = "registro exitoso - Token en header Authorization",
            headers = @io.swagger.v3.oas.annotations.headers.Header(
                    name = "Authorization",
                    description = "Bearer token",
                    schema = @Schema(type = "string")
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales inválidas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<LoginResponseDTO> register(
            @RequestBody @Valid UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = authService.registrarUsuario(usuarioDTO.aModelo(), usuarioDTO.carreraId());

        LoginResponseDTO response = authService.generarRespuestaPostRegistro(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario")
    @ApiResponse(
            responseCode = "200",
            description = "Login exitoso - Token en header Authorization",
            headers = @io.swagger.v3.oas.annotations.headers.Header(
                    name = "Authorization",
                    description = "Bearer token",
                    schema = @Schema(type = "string")
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales inválidas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequest) {

        LoginResponseDTO response = authService.autenticarUsuario(loginRequest.aModelo());
        return ResponseEntity.ok(response);
    }
}