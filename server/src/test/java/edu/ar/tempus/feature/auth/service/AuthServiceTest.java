package edu.ar.tempus.feature.auth.service;

import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.service.ResetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private MateriaService materiaService;

    private String email;
    private Long carreraId;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {
        email = "juan.perez@mail.com";

        Materia ingles = materiaService.guardar(Materia.builder()
                .materiaNombre("Ingles I").correlativas(new HashSet<>()).build());

        Carrera carrera = carreraService.guardar(
                Carrera.builder().nombreCarrera("Ingeniería en Sistemas").build(),
                Set.of(ingles.getMateriaId())
        );

        carreraId = carrera.getId();

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

        usuario1 = authService.registrar(usuario1, carreraId);
        usuario2 = authService.registrar(usuario2, carreraId);
    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}