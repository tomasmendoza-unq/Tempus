package edu.ar.tempus.controller;

import edu.ar.tempus.service.UsuarioService;
import edu.ar.tempus.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/anotarse/{comisionesId}")
    public ResponseEntity<String> anotarseAComisiones(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.anotarseAComision(comisionId, authUtils.getAlumnoId(authentication)); //aca se podria generar un certificado

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

}
