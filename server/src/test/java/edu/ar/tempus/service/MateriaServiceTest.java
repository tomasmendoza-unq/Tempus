package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional("transactionManager")
@Rollback
public class MateriaServiceTest {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ResetService resetService;

    private Materia lea;

    @BeforeEach
    public void setUp() {
        lea = Materia.builder()
                .materiaNombre("LEA")
                .correlativas(new HashSet<>())
                .build();
    }

    @Test
    public void crearMateriaConCorrelativas(){
        Materia leaGuardada = materiaService.guardar(lea);
        assertNotNull(leaGuardada.getMateriaId(), "LEA debe tener ID después de guardar");

        Set<Materia> correlativas = new HashSet<>();
        correlativas.add(leaGuardada);

        Materia ingles = Materia.builder()
                .materiaNombre("ingles")
                .correlativas(correlativas)
                .build();

        Materia inglesGuardada = materiaService.guardar(ingles);
        assertNotNull(inglesGuardada.getMateriaId(), "Ingles debe tener ID después de guardar");

        Materia inglesRecuperada = materiaService.recuperar(inglesGuardada.getMateriaId());

        assertNotNull(inglesRecuperada);
        assertEquals(inglesGuardada.getMateriaId(), inglesRecuperada.getMateriaId());
        assertNotNull(inglesRecuperada.getCorrelativas());
        assertFalse(inglesRecuperada.getCorrelativas().isEmpty(), "Ingles debe tener correlativas");

        boolean hasLea = inglesRecuperada.getCorrelativas().stream()
                .anyMatch(m -> m.getMateriaId().equals(leaGuardada.getMateriaId()));
        assertTrue(hasLea, "Ingles debe tener LEA como correlativa");
    }

    @Test
    public void asociarMateriaSimpleConOtra(){

        Materia leaGuardada = materiaService.guardar(lea);
        assertNotNull(leaGuardada.getMateriaId(), "LEA debe tener ID después de guardar");

        Materia lea2 = Materia.builder()
                .materiaNombre("LEA2")
                .correlativas(new HashSet<>())
                .build();

        Materia leaGuardada2 = materiaService.guardar(lea2);
        assertNotNull(leaGuardada2.getMateriaId(), "LEA2 debe tener ID después de guardar");

        materiaService.asociarMateria(leaGuardada2.getMateriaId(), leaGuardada.getMateriaId());


        Materia leaRecuperada = materiaService.recuperar(lea.getMateriaId());
        Materia leaRecuperada2 = materiaService.recuperar(lea2.getMateriaId());

        boolean hasLea = leaRecuperada2.getCorrelativas().stream()
                .anyMatch(m -> m.getMateriaId().equals(leaRecuperada.getMateriaId()));
        assertTrue(hasLea, "LEA2 debe tener LEA como correlativa");
    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}