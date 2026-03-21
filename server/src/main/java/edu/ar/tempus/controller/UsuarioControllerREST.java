package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDetallesDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseSimpleDTO;
import edu.ar.tempus.model.usuario.Usuario;
import edu.ar.tempus.service.UsuarioService;
import edu.ar.tempus.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControllerREST {

    private final AuthUtils authUtils;

    private final UsuarioService usuarioService;

    public UsuarioControllerREST(AuthUtils authUtils, UsuarioService usuarioService) {
        this.authUtils = authUtils;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UsuarioResponseDetallesDTO> obtenerDetallesCompletos(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        Usuario usuario = usuarioService.recuperarUsuarioPorId(alumnoId);

        UsuarioResponseDetallesDTO response = UsuarioResponseDetallesDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/anotarse/{comisionesId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> anotarseAComisiones(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.anotarseAComision(comisionId, authUtils.getAlumnoId(authentication)); //aca se podria generar un certificado

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/aprobar/{comisionesId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> AprobarAMateria(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.aprobarMaterias(comisionId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/desaprobar/{materiaId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> DesaprobarMateria(@PathVariable("materiaId") Long materiaId, Authentication authentication) {
        usuarioService.desaprobarMateria(materiaId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/suscribir/carrera/{carreraId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> suscribirseACarrera(@PathVariable("carreraId") Long carreraId, Authentication authentication) {
        usuarioService.suscribirseACarrera(carreraId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizó con éxito la operación");
    }

    @GetMapping("/perfil")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UsuarioResponseSimpleDTO>  obtenerDetallesSimple(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        Usuario usuario = usuarioService.recuperarUsuarioPorId(alumnoId);

        UsuarioResponseSimpleDTO response = UsuarioResponseSimpleDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/carreras/{carreraId}/activar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> seleccionarCarrera(@PathVariable Long carreraId, Authentication authentication) {
        usuarioService.seleccionarCarreraActiva(
                carreraId,
                authUtils.getAlumnoId(authentication)
        );

        return ResponseEntity.ok("Carrera actualizada");
    }
}
