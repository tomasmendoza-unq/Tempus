package edu.ar.tempus.service;

import edu.ar.tempus.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HorarioServiceTest {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private ComisionService comisionService;
    @Autowired
    private ResetService resetService;

    private Materia lea, lea2, lea3;
    private Comision leaManana, lea2Tarde, lea3Noche, leaTarde;

    @BeforeEach
    public void setUp() {
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
    public void seGeneraUnSoloHorarioParaLea123() {
        List<Long> materiasIds = List.of(lea.getMateriaId(), lea2.getMateriaId(), lea3.getMateriaId());

        Optional<Horario> horarioOpt = horarioService.generarUnHorarioCon(materiasIds);

        assertTrue(horarioOpt.isPresent());
        Horario horario = horarioOpt.get();

        assertEquals(3, horario.getComisiones().size());

        assertTrue(horario.getComisiones().stream()
                .anyMatch(c -> c.getClases().stream()
                        .anyMatch(clase -> clase.getInicio().equals(LocalTime.of(8, 0)))));

        assertFalse(horario.getComisiones().stream()
                .anyMatch(c -> c.getMateria().getMateriaId().equals(lea.getMateriaId())
                        && c.getClases().stream()
                        .anyMatch(clase -> clase.getInicio().equals(LocalTime.of(14, 0)))));
    }
    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}