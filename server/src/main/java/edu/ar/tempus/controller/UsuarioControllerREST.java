package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDetallesDTO;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.UsuarioService;
import edu.ar.tempus.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public final class UsuarioControllerREST {

    private final AuthUtils authUtils;

    private final UsuarioService usuarioService;

    public UsuarioControllerREST(AuthUtils authUtils, UsuarioService usuarioService) {
        this.authUtils = authUtils;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<UsuarioResponseDetallesDTO> obtenerTodosUsuarios(Authentication authentication) {
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
}
