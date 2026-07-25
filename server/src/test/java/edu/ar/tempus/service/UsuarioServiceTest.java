package edu.ar.tempus.service;

import edu.ar.tempus.exceptions.business.*;
import edu.ar.tempus.feature.alumno.exception.YaSeEncuentraSuscritoALaCarrera;
import edu.ar.tempus.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResetService  resetService;

    private String email ;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {

        email = "juan.perez@mail.com";

        usuario1 = Usuario.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .role(Role.USER)
                .build();

        usuario2 = Usuario.builder()
                .email("maria.gonzalez@mail.com")
                .password("password456")
                .nombre("María")
                .apellido("González")
                .role(Role.ADMIN)
                .build();

        usuario1 = usuarioService.guardarUsuario(usuario1);

        usuario2 = usuarioService.guardarUsuario(usuario2);
    }


    @Test
    public void recuperarUsuarioPorEmail() {

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        assertEquals(email, usuarioRecuperado.getEmail());

    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
