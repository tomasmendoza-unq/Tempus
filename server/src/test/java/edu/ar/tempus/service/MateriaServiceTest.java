package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
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
@Transactional("transactionManager")  // Especificar el transaction manager
@Rollback  // Hacer rollback después de cada test
public class MateriaServiceTest {

    @Autowired
    private MateriaService materiaService;

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
        // Guardar LEA
        Materia leaGuardada = materiaService.guardar(lea);
        assertNotNull(leaGuardada.getMateriaId(), "LEA debe tener ID después de guardar");

        // Crear Ingles con LEA como correlativa
        Set<Materia> correlativas = new HashSet<>();
        correlativas.add(leaGuardada);

        Materia ingles = Materia.builder()
                .materiaNombre("ingles")
                .correlativas(correlativas)
                .build();

        // Guardar Ingles
        Materia inglesGuardada = materiaService.guardar(ingles);
        assertNotNull(inglesGuardada.getMateriaId(), "Ingles debe tener ID después de guardar");

        // Recuperar y verificar
        Materia inglesRecuperada = materiaService.recuperar(inglesGuardada.getMateriaId());

        assertNotNull(inglesRecuperada);
        assertEquals(inglesGuardada.getMateriaId(), inglesRecuperada.getMateriaId());
        assertNotNull(inglesRecuperada.getCorrelativas());
        assertFalse(inglesRecuperada.getCorrelativas().isEmpty(), "Ingles debe tener correlativas");

        // Verificar que LEA está en las correlativas
        boolean hasLea = inglesRecuperada.getCorrelativas().stream()
                .anyMatch(m -> m.getMateriaId().equals(leaGuardada.getMateriaId()));
        assertTrue(hasLea, "Ingles debe tener LEA como correlativa");
    }
}