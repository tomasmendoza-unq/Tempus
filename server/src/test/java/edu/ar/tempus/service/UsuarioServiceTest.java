package edu.ar.tempus.service;

import edu.ar.tempus.exceptions.business.AlumnoAnotadoAOtraComisionException;
import edu.ar.tempus.exceptions.business.EmailYaExisteException;
import edu.ar.tempus.exceptions.business.SuperPosicionDeHorariosException;
import edu.ar.tempus.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private ComisionService comisionService;

    private Materia lea, lea2, lea3;
    private Comision leaManana, lea2Tarde, lea3Noche, leaTarde;

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

        usuario1 = usuarioService.guardarUsuario(usuario1);

        usuario2 = usuarioService.guardarUsuario(usuario2);

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

        assertThrows(EmailYaExisteException.class, () -> usuarioService.guardarUsuario(usuarioError));

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
    }


    @Test
    public void intentaAnotarseAUnaComisionDeUnaMateriaQueYaEstabaAnotado(){
        List<Long> comisiones = new ArrayList<>(List.of(leaTarde.getComisionId(), leaManana.getComisionId()));

        assertThrows(AlumnoAnotadoAOtraComisionException.class, () -> usuarioService.anotarseAComision(comisiones, usuario1.getId()));
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
