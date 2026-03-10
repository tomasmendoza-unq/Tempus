package edu.ar.tempus.service;

import edu.ar.tempus.exceptions.business.*;
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
    private HorarioService horarioService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionService comisionService;

    @Autowired
    private CarreraService carreraService;

    private Materia lea, lea2, lea3;
    private Comision leaManana, lea2Tarde, lea3Noche, leaTarde;

    private Carrera informatica, sistemas, sistemas2;

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
                .telefono("221-4567890")
                .role(Role.USER)
                .build();

        usuario2 = Usuario.builder()
                .email("maria.gonzalez@mail.com")
                .password("password456")
                .nombre("María")
                .apellido("González")
                .telefono("351-9876543")
                .role(Role.ADMIN)
                .build();



        lea = materiaService.guardar(Materia.builder().materiaNombre("LEA").correlativas(new HashSet<>()).build());
        lea2 = materiaService.guardar(Materia.builder().materiaNombre("LEA2").correlativas(new HashSet<>()).build());
        lea3 = materiaService.guardar(Materia.builder().materiaNombre("LEA3").correlativas(new HashSet<>(Set.of(lea2))).build());

        leaManana = comisionService.guardar(Comision.builder()
                .clases(List.of(ClaseHorario.builder().dia(DiasSemana.LUNES).inicio(LocalTime.of(8, 0)).fin(LocalTime.of(10, 0)).build()))
                .build(), lea.getMateriaId());


        lea2Tarde = comisionService.guardar(Comision.builder()
                .clases(List.of(ClaseHorario.builder().dia(DiasSemana.LUNES).inicio(LocalTime.of(14, 0)).fin(LocalTime.of(16, 0)).build()))
                .build(), lea2.getMateriaId());


        lea3Noche = comisionService.guardar(Comision.builder()
                .clases(List.of(ClaseHorario.builder().dia(DiasSemana.LUNES).inicio(LocalTime.of(18, 0)).fin(LocalTime.of(20, 0)).build()))
                .build(), lea3.getMateriaId());


        leaTarde = comisionService.guardar(Comision.builder()
                .clases(List.of(ClaseHorario.builder().dia(DiasSemana.LUNES).inicio(LocalTime.of(14, 0)).fin(LocalTime.of(16, 0)).build()))
                .build(), lea.getMateriaId());

        informatica = Carrera.builder()
                .nombreCarrera("Lic. en informatica")
                .build();

        Carrera carreraGuardada = carreraService.guardar(informatica, Set.of(lea.getMateriaId(), lea2.getMateriaId(), lea3.getMateriaId()));

        sistemas = Carrera.builder()
                .nombreCarrera("Lic. en sistemas")
                .build();

        sistemas = carreraService.guardar(
                sistemas,
                Set.of(lea.getMateriaId())
        );

        sistemas2 = Carrera.builder()
                .nombreCarrera("Lic. en sistemas1234")
                .build();

        sistemas2 = carreraService.guardar(
                sistemas2,
                Set.of(lea.getMateriaId())
        );

        usuario1 = usuarioService.guardarUsuario(usuario1, sistemas2.getId());

        usuario2 = usuarioService.guardarUsuario(usuario2, sistemas2.getId());
    }


    @Test
    public void recuperarUsuarioPorEmail() {

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        assertEquals(email, usuarioRecuperado.getEmail());

    }

    @Test
    public void errorAlIntentarCargarDosUsuariosConElMismoEmail() {
        Usuario usuarioError = Usuario.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .telefono("221-4567890")
                .role(Role.USER)
                .build();

        assertThrows(EmailYaExisteException.class, () -> usuarioService.guardarUsuario(usuarioError, sistemas2.getId()));

    }

    @Test
    public void anotarseAUnaComision(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        usuarioService.anotarseAComision(comisiones, usuario1.getId());

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorId(usuario1.getId());

        assertTrue(comisiones.stream()
                .allMatch(comisionId -> usuarioRecuperado.getComisiones().stream()
                        .anyMatch(c -> c.getComisionId().equals(comisionId))
                )
        );
    }

    @Test
    public void intentaAnotarseAUnaComisionPeroHaySuperposicion(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea2Tarde.getComisionId()));

        assertThrows(SuperPosicionDeHorariosException.class, () -> usuarioService.anotarseAComision(comisiones, usuario1.getId()));

        usuarioService.anotarseAComision(List.of(leaTarde.getComisionId()), usuario1.getId());

        assertThrows(SuperPosicionDeHorariosException.class, () -> usuarioService.anotarseAComision(List.of(lea2Tarde.getComisionId()), usuario1.getId()));

    }


    @Test
    public void intentaAnotarseAUnaComisionDeUnaMateriaQueYaEstabaAnotado(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), leaManana.getComisionId()));

        assertThrows(AlumnoAnotadoAOtraComisionException.class, () -> usuarioService.anotarseAComision(comisiones, usuario1.getId()));

        usuarioService.anotarseAComision(List.of(leaTarde.getComisionId()), usuario1.getId());

        assertThrows(AlumnoAnotadoAOtraComisionException.class, () -> usuarioService.anotarseAComision(List.of(leaManana.getComisionId()), usuario1.getId()));

    }

    @Test
    public void anotarComoAprobadaUnaComision(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        usuarioService.anotarseAComision(comisiones, usuario1.getId());

        usuarioService.aprobarMaterias(List.of(leaTarde.getComisionId()), usuario1.getId());

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorId(usuario1.getId());

        assertTrue(usuarioRecuperado.getMateriasAprobadas().stream()
                .anyMatch(m -> m.getMateriaId().equals(lea.getMateriaId()))
        );


        assertFalse(usuarioRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(leaTarde.getComisionId()))
        );

        assertTrue(usuarioRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(lea3Noche.getComisionId()))
        );

        assertThrows(MateriaYaAprobadaException.class, () ->
                usuarioService.aprobarMaterias(List.of(leaTarde.getComisionId()), usuario1.getId()));
    }

    @Test
    public void desaprobarUnaMateria(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        usuarioService.anotarseAComision(comisiones, usuario1.getId());
        usuarioService.aprobarMaterias(List.of(leaTarde.getComisionId()), usuario1.getId());
        usuarioService.desaprobarMateria(leaTarde.getMateria().getMateriaId(), usuario1.getId());

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorId(usuario1.getId());

        assertFalse(usuarioRecuperado.getMateriasAprobadas().stream()
                .anyMatch(m -> m.getMateriaId().equals(lea.getMateriaId()))
        );

        assertFalse(usuarioRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(leaTarde.getComisionId()))
        );

        assertTrue(usuarioRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(lea3Noche.getComisionId()))
        );

        assertDoesNotThrow(() ->
                usuarioService.aprobarMaterias(List.of(leaTarde.getComisionId()), usuario1.getId()));
    }

    @Test
    public void suscribirseAUnaCarrera(){
        usuarioService.suscribirseACarrera(informatica.getId(), usuario1.getId());

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorId(usuario1.getId());

        assertTrue(usuarioRecuperado.getCarreras().stream().anyMatch(c -> c.getId().equals(informatica.getId())));
    }
/*
    @Test
    public void intentaAnotarseAComisionPeroNoTieneLasCorrelativas() {

        List<Long> comisiones = List.of(lea3Noche.getComisionId());

        assertThrows(
                AlumnoNoCuentaConLasCorrelativasException.class,
                () -> usuarioService.anotarseAComision(comisiones, usuario1.getId())
        );
    }
*/
    @Test
    public void intentaSuscribirseALaCarrera(){
        usuarioService.suscribirseACarrera(informatica.getId(), usuario1.getId());
        assertThrows(YaSeEncuentraSuscritoALaCarrera.class, () -> usuarioService.suscribirseACarrera(informatica.getId(), usuario1.getId()));
    }

    @Test
    public void cambiarCarreraActiva(){
        usuarioService.suscribirseACarrera(informatica.getId(), usuario1.getId());
        usuarioService.suscribirseACarrera(sistemas.getId(), usuario1.getId());

        usuarioService.seleccionarCarreraActiva(sistemas.getId(), usuario1.getId());

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorId(usuario1.getId());

        assertEquals(
                sistemas.getId(),
                usuarioRecuperado.getCarreraActiva().getId()
        );
    }

    @Test
    public void intentaSeleccionarCarreraQueNoLePertenece(){
        usuarioService.suscribirseACarrera(informatica.getId(), usuario1.getId());

        assertThrows(
                UsuarioNoPerteneceALaCarreraException.class,
                () -> usuarioService.seleccionarCarreraActiva(sistemas.getId(), usuario1.getId())
        );
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
