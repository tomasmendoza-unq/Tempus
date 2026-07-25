package edu.ar.tempus.feature.alumno.service;

import edu.ar.tempus.exceptions.business.*;
import edu.ar.tempus.feature.alumno.exception.YaSeEncuentraSuscritoALaCarrera;
import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.model.*;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.service.ResetService;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AlumnoServiceTest {

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
    private AlumnoService alumnoService;

    @Autowired
    private ResetService resetService;

    private String email;

    private Alumno alumno1;
    private Alumno alumno2;

    @BeforeEach
    public void setUp() {

        email = "juan.perez@mail.com";

        alumno1 = Alumno.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .role(Role.ALUMNO)
                .build();

        alumno2 = Alumno.builder()
                .email("maria.gonzalez@mail.com")
                .password("password456")
                .nombre("María")
                .apellido("González")
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

        informatica = carreraService.guardar(informatica, Set.of(lea.getMateriaId(), lea2.getMateriaId(), lea3.getMateriaId()));

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

        alumno1 = alumnoService.guardar(alumno1, sistemas2.getId());

        alumno2 = alumnoService.guardar(alumno2, sistemas2.getId());
    }

    @Test
    public void errorAlIntentarCargarDosAlumnosConElMismoEmail() {
        Alumno alumnoError = Alumno.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .role(Role.ALUMNO)
                .build();

        assertThrows(EmailYaExisteException.class, () -> alumnoService.guardar(alumnoError, sistemas2.getId()));

    }

    @Test
    public void anotarseAUnaComision() {
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        alumnoService.anotarseAComision(comisiones, alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertTrue(comisiones.stream()
                .allMatch(comisionId -> alumnoRecuperado.getComisiones().stream()
                        .anyMatch(c -> c.getComisionId().equals(comisionId))
                )
        );
    }

    @Test
    public void intentaAnotarseAUnaComisionPeroHaySuperposicion() {
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea2Tarde.getComisionId()));

        assertThrows(SuperPosicionDeHorariosException.class, () -> alumnoService.anotarseAComision(comisiones, alumno1.getId()));

        alumnoService.anotarseAComision(List.of(leaTarde.getComisionId()), alumno1.getId());

        assertThrows(SuperPosicionDeHorariosException.class, () -> alumnoService.anotarseAComision(List.of(lea2Tarde.getComisionId()), alumno1.getId()));

    }

    @Test
    public void intentaAnotarseAUnaComisionDeUnaMateriaQueYaEstabaAnotado() {
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), leaManana.getComisionId()));

        assertThrows(AlumnoAnotadoAOtraComisionException.class, () -> alumnoService.anotarseAComision(comisiones, alumno1.getId()));

        alumnoService.anotarseAComision(List.of(leaTarde.getComisionId()), alumno1.getId());

        assertThrows(AlumnoAnotadoAOtraComisionException.class, () -> alumnoService.anotarseAComision(List.of(leaManana.getComisionId()), alumno1.getId()));

    }

    @Test
    public void anotarComoAprobadaUnaComision() {
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        alumnoService.anotarseAComision(comisiones, alumno1.getId());

        alumnoService.aprobarMaterias(List.of(leaTarde.getComisionId()), alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertTrue(alumnoRecuperado.getMateriasAprobadas().stream()
                .anyMatch(m -> m.getMateriaId().equals(lea.getMateriaId()))
        );

        assertFalse(alumnoRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(leaTarde.getComisionId()))
        );

        assertTrue(alumnoRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(lea3Noche.getComisionId()))
        );

        assertThrows(MateriaYaAprobadaException.class, () ->
                alumnoService.aprobarMaterias(List.of(leaTarde.getComisionId()), alumno1.getId()));
    }

    @Test
    public void desaprobarUnaMateria() {
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), lea3Noche.getComisionId()));
        alumnoService.anotarseAComision(comisiones, alumno1.getId());
        alumnoService.aprobarMaterias(List.of(leaTarde.getComisionId()), alumno1.getId());
        alumnoService.desaprobarMateria(leaTarde.getMateria().getMateriaId(), alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertFalse(alumnoRecuperado.getMateriasAprobadas().stream()
                .anyMatch(m -> m.getMateriaId().equals(lea.getMateriaId()))
        );

        assertFalse(alumnoRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(leaTarde.getComisionId()))
        );

        assertTrue(alumnoRecuperado.getComisiones().stream()
                .anyMatch(c -> c.getComisionId().equals(lea3Noche.getComisionId()))
        );

        assertDoesNotThrow(() ->
                alumnoService.aprobarMaterias(List.of(leaTarde.getComisionId()), alumno1.getId()));
    }

    @Test
    public void suscribirseAUnaCarrera() {
        alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertTrue(alumnoRecuperado.getCarreras().stream().anyMatch(c -> c.getId().equals(informatica.getId())));
    }

    @Test
    public void intentaSuscribirseALaCarrera() {
        alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId());
        assertThrows(YaSeEncuentraSuscritoALaCarrera.class, () -> alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId()));
    }

    @Test
    public void cambiarCarreraActiva() {
        alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId());
        alumnoService.suscribirseACarrera(sistemas.getId(), alumno1.getId());

        alumnoService.seleccionarCarreraActiva(sistemas.getId(), alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertEquals(
                sistemas.getId(),
                alumnoRecuperado.getCarreraActiva().getId()
        );
    }

    @Test
    public void intentaSeleccionarCarreraQueNoLePertenece() {
        alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId());

        assertThrows(
                UsuarioNoPerteneceALaCarreraException.class,
                () -> alumnoService.seleccionarCarreraActiva(sistemas.getId(), alumno1.getId())
        );
    }

    //UNA MATERIA ESTA DISPONIBLE SI SE PUEDE CURSAR EN EL PROXIMO CUATRI
    @Test
    public void recuperarMateriasDisponibles_NoDebeRetornarMateriasDeOtrasCarreras() {
        Alumno alumno = alumnoService.guardar(
                Alumno.builder()
                        .email("test.aislamiento@mail.com")
                        .password("123456")
                        .nombre("Test")
                        .apellido("Aislamiento")
                        .role(Role.ALUMNO)
                        .build(),
                sistemas.getId()
        );

        Materia anatomia = materiaService.guardar(Materia.builder()
                .materiaNombre("Anatomía")
                .build());

        Carrera medicina = carreraService.guardar(
                Carrera.builder().nombreCarrera("Medicina").build(),
                Set.of(anatomia.getMateriaId())
        );

        List<Materia> materiasDisponibles = alumnoService.recuperarMateriasDisponibles(alumno.getId());

        Set<Long> idsDisponibles = materiasDisponibles.stream()
                .map(Materia::getMateriaId)
                .collect(Collectors.toSet());

        assertTrue(idsDisponibles.contains(lea.getMateriaId()),
                "Debe incluir materias de la carrera del usuario");

        assertFalse(idsDisponibles.contains(anatomia.getMateriaId()),
                "No debe incluir materias de carreras a las que el usuario no pertenece");

        assertEquals(1, materiasDisponibles.size(),
                "El usuario solo debería ver 1 materia disponible de su carrera actual");
    }
    @Test
    public void desuscribirseACarreraAlQueEstaSuscripto() {
        alumnoService.suscribirseACarrera(informatica.getId(), alumno1.getId());

        alumnoService.desuscribirseACarrera(informatica.getId(), alumno1.getId());

        Alumno alumnoRecuperado = alumnoService.getAlumnoById(alumno1.getId());

        assertFalse(alumnoRecuperado.getCarreras().stream()
                .anyMatch(c -> c.getId().equals(informatica.getId())));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}