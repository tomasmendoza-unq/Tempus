package edu.ar.tempus.feature.usuario.controller;

import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDetallesDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseSimpleDTO;
import edu.ar.tempus.controller.exceptions.ErrorResponseDTO;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.UsuarioService;
import edu.ar.tempus.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public final class UsuarioControllerREST {

    private final AuthUtils authUtils;

    private final UsuarioService usuarioService;

    public UsuarioControllerREST(AuthUtils authUtils, UsuarioService usuarioService) {
        this.authUtils = authUtils;
        this.usuarioService = usuarioService;
    }

    @GetMapping
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
    public ResponseEntity<UsuarioResponseDetallesDTO> getPerfil(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        Usuario usuario = usuarioService.recuperarUsuarioPorId(alumnoId);

        UsuarioResponseDetallesDTO response = UsuarioResponseDetallesDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/anotarse/{comisionesId}")
    public ResponseEntity<String> anotarseAComisiones(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.anotarseAComision(comisionId, authUtils.getAlumnoId(authentication)); //aca se podria generar un certificado

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/aprobar/{comisionesId}")
    public ResponseEntity<String> AprobarAMateria(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.aprobarMaterias(comisionId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/desaprobar/{materiaId}")
    public ResponseEntity<String> DesaprobarMateria(@PathVariable("materiaId") Long materiaId, Authentication authentication) {
        usuarioService.desaprobarMateria(materiaId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/suscribir/carrera/{carreraId}")
    public ResponseEntity<String> suscribirseACarrera(@PathVariable("carreraId") Long carreraId, Authentication authentication) {
        usuarioService.suscribirseACarrera(carreraId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizó con éxito la operación");
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseSimpleDTO>  obtenerDetallesSimple(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        Usuario usuario = usuarioService.recuperarUsuarioPorId(alumnoId);

        UsuarioResponseSimpleDTO response = UsuarioResponseSimpleDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/carreras/{carreraId}/activar")
    public ResponseEntity<String> seleccionarCarrera(@PathVariable Long carreraId, Authentication authentication) {
        usuarioService.seleccionarCarreraActiva(
                carreraId,
                authUtils.getAlumnoId(authentication)
        );

        return ResponseEntity.ok("Carrera actualizada");
    }
}
