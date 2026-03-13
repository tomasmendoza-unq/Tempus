package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClaseHorarioServiceTest {

    @Autowired
    private ClaseHorarioService claseHorarioService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionService comisionService;

    @Autowired
    private ResetService resetService;

    private Comision comision;

    @BeforeEach
    public void setUp() {

        Materia materia = materiaService.guardar(
                Materia.builder()
                        .materiaNombre("Programacion")
                        .correlativas(Set.of())
                        .build()
        );

        comision = comisionService.guardar(
                Comision.builder()
                        .clases(List.of(
                                ClaseHorario.builder()
                                        .dia(DiasSemana.LUNES)
                                        .inicio(LocalTime.of(8,0))
                                        .fin(LocalTime.of(10,0))
                                        .build(),
                                ClaseHorario.builder()
                                        .dia(DiasSemana.MIERCOLES)
                                        .inicio(LocalTime.of(10,0))
                                        .fin(LocalTime.of(12,0))
                                        .build()
                        ))
                        .build(),
                materia.getMateriaId()
        );
    }

    @Test
    public void seActualizaUnHorarioCorrectamente() {

        ClaseHorario clase = comision.getClases().getFirst();

        UpdateClaseHorarioDTORequest dto =
                new UpdateClaseHorarioDTORequest(
                        clase.getId(),
                        DiasSemana.MARTES,
                        LocalTime.of(9,0),
                        LocalTime.of(11,0)
                );

        List<ClaseHorario> resultado = claseHorarioService.actualizar(List.of(dto));

        assertEquals(1, resultado.size());

        ClaseHorario actualizado = resultado.getFirst();

        assertEquals(DiasSemana.MARTES, actualizado.getDia());
        assertEquals(LocalTime.of(9,0), actualizado.getInicio());
        assertEquals(LocalTime.of(11,0), actualizado.getFin());
    }

    @Test
    public void seActualizanMultiplesHorarios() {

        ClaseHorario clase1 = comision.getClases().get(0);
        ClaseHorario clase2 = comision.getClases().get(1);

        UpdateClaseHorarioDTORequest dto1 =
                new UpdateClaseHorarioDTORequest(
                        clase1.getId(),
                        DiasSemana.MARTES,
                        LocalTime.of(9,0),
                        LocalTime.of(11,0)
                );

        UpdateClaseHorarioDTORequest dto2 =
                new UpdateClaseHorarioDTORequest(
                        clase2.getId(),
                        DiasSemana.JUEVES,
                        LocalTime.of(14,0),
                        LocalTime.of(16,0)
                );

        List<ClaseHorario> resultado = claseHorarioService.actualizar(List.of(dto1, dto2));

        assertEquals(2, resultado.size());

        assertTrue(resultado.stream()
                .anyMatch(c -> c.getDia() == DiasSemana.MARTES
                        && c.getInicio().equals(LocalTime.of(9,0))));

        assertTrue(resultado.stream()
                .anyMatch(c -> c.getDia() == DiasSemana.JUEVES
                        && c.getInicio().equals(LocalTime.of(14,0))));
    }

    @Test
    public void fallaCuandoFinEsAnteriorAInicio() {

        ClaseHorario clase = comision.getClases().getFirst();

        UpdateClaseHorarioDTORequest dto =
                new UpdateClaseHorarioDTORequest(
                        clase.getId(),
                        DiasSemana.LUNES,
                        LocalTime.of(12,0),
                        LocalTime.of(10,0)
                );

        assertThrows(Exception.class, () ->
                claseHorarioService.actualizar(List.of(dto))
        );
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}