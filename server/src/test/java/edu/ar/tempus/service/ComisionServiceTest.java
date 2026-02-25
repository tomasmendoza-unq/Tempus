package edu.ar.tempus.service;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.DiasSemana;
import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComisionServiceTest {

    @Autowired
    private ResetService resetService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionService comisionService;

    private Materia inglesGuardada;

    @BeforeEach
    public void setUp() {
        Materia ingles = Materia.builder()
                .materiaNombre("Ingles")
                .correlativas(new HashSet<>())
                .build();

        inglesGuardada = materiaService.guardar(ingles);
    }

    @Test
    public void crearUnaComisionConMultiplesHorariosYRecuperar() {
        ClaseHorario lunes = ClaseHorario.builder()
                .dia(DiasSemana.LUNES)
                .inicio(LocalTime.of(8, 0))
                .fin(LocalTime.of(10, 0))
                .build();

        ClaseHorario miercoles = ClaseHorario.builder()
                .dia(DiasSemana.MIERCOLES)
                .inicio(LocalTime.of(9, 0))
                .fin(LocalTime.of(11, 0))
                .build();

        Comision nuevaComision = Comision.builder()
                .clases(List.of(lunes, miercoles))
                .build();

        Comision comisionGuardada = comisionService.guardar(nuevaComision, inglesGuardada.getMateriaId());

        Comision comisionRecuperada = comisionService.recuperar(comisionGuardada.getComisionId());

        assertEquals(comisionGuardada.getComisionId(), comisionRecuperada.getComisionId());
        assertEquals(2, comisionRecuperada.getClases().size(), "Debería tener 2 horarios");

        assertEquals(DiasSemana.LUNES, comisionRecuperada.getClases().getFirst().getDia());
        assertEquals(LocalTime.of(8, 0), comisionRecuperada.getClases().getFirst().getInicio());

        assertEquals(inglesGuardada.getMateriaId(), comisionRecuperada.getMateria().getMateriaId());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}