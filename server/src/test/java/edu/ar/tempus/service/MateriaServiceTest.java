package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MateriaServiceTest {

    @Autowired
    private MateriaService materiaService;

    private Materia ingles;

    @BeforeEach
    public void setUp() {
        ingles = Materia.builder().materiaNombre("ingles").build();
    }

    @Test
    public void crearMateriaSinCorrelativas(){

        Materia materiaGuardada = materiaService.guardar(ingles);

        Materia materiaRecuperada = materiaService.recuperar(materiaGuardada.getMateriaId());

        assertEquals(materiaGuardada.getMateriaId(), materiaRecuperada.getMateriaId());
    }

}
