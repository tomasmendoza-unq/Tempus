package edu.ar.tempus.service;

import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional("transactionManager")
public class CarreraServiceTest {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private CarreraService carreraService;

    private Materia lea;
    private Materia leaGuardada;

    private Carrera carrera, carrera2;

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;


    @BeforeEach
    public void setUp() {
        lea = Materia.builder()
                .materiaNombre("LEA")
                .correlativas(new HashSet<>())
                .build();

        leaGuardada = materiaService.guardar(lea);

        carrera = Carrera.builder()
                .nombreCarrera("Lic. en informatica")
                .build();

        carrera2 = Carrera.builder()
                .nombreCarrera("informatica46")
                .build();

        usuario = Usuario.builder()
                .email("juan.perez@mail.com")
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .telefono("221-4567890")
                .role(Role.USER)
                .build();


        Carrera carreraGuardada = carreraService.guardar(carrera2, Set.of(leaGuardada.getMateriaId()));

        usuario = usuarioService.guardarUsuario(usuario, carreraGuardada.getId());
    }

    @Test
    public void crearUnaCarreraYRecuperarla(){
        Carrera carreraGuardada = carreraService.guardar(carrera, Set.of(leaGuardada.getMateriaId()));

        Carrera carreraRecuperada = carreraService.recuperar(carreraGuardada.getId());

        assertEquals(carreraGuardada.getNombreCarrera(), carreraRecuperada.getNombreCarrera());
    }

    @Test
    public void recuperarCarreraPorNombre(){
        List<Carrera> carreras = carreraService.recuperarCarreraPorNombre("Lic");


        assertTrue(carreras.stream().allMatch(c -> c.getNombreCarrera().toLowerCase().contains("Lic")));
    }

    @Test
    public void recuperarCarrerasPorAlumno() {
        Carrera carreraGuardada = carreraService.guardar(carrera, Set.of(leaGuardada.getMateriaId()));

        Carrera otraCarrera = carreraService.guardar(
                Carrera.builder().nombreCarrera("Lic. en sistemas").build(),
                Set.of(leaGuardada.getMateriaId())
        );


        usuarioService.suscribirseACarrera(carreraGuardada.getId(), usuario.getId());

        List<Carrera> carrerasNoSuscripto = carreraService.recuperarCarrerasPorAlumno(usuario.getId());

        assertFalse(carrerasNoSuscripto.stream()
                .anyMatch(c -> c.getId().equals(carreraGuardada.getId()))
        );

        assertTrue(carrerasNoSuscripto.stream()
                .anyMatch(c -> c.getId().equals(otraCarrera.getId()))
        );
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
