package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComisionServiceTest {

    @Autowired
    private ResetService resetService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionService comisionService;

    private Comision comision;

    private Materia ingles;

    private Materia inglesGuardada;


    @BeforeEach
    public void setUp() {
        ingles= Materia.builder()
                .materiaNombre("Ingles")
                .correlativas(new HashSet<>())
                .build();

        inglesGuardada = materiaService.guardar(ingles);

        LocalTime horarioInicio = LocalTime.of(8,0);
        LocalTime horarioFin = LocalTime.of(10,0);

        comision = Comision.builder()
                    .horarioInicio(horarioInicio)
                    .horarioFin(horarioFin)
                .build();
    }


    @Test
    public void crearUnaComisionYRecuperar(){

        Comision comisionGuardada = comisionService.guardar(comision, inglesGuardada.getMateriaId());

        Comision comisionRecuperada = comisionService.recuperar(comisionGuardada.getComisionId());

        assertEquals(comisionGuardada.getComisionId(), comisionRecuperada.getComisionId());
        assertEquals(comisionGuardada.getHorarioInicio(), comisionRecuperada.getHorarioInicio());
        assertEquals(comisionGuardada.getHorarioFin(), comisionRecuperada.getHorarioFin());
        assertEquals(comisionGuardada.getMateria().getMateriaId(), comisionRecuperada.getMateria().getMateriaId());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }

}
