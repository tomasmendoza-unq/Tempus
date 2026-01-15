package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    private Materia ingles;
    
    private Materia lea2;

    private Materia leaGuardada;

    private Materia inglesGuardada;

    private Materia leaGuardada2;

    @BeforeEach
    public void setUp() {
        lea = Materia.builder()
                .materiaNombre("LEA")
                .correlativas(new HashSet<>())
                .build();

        lea2 = Materia.builder()
                .materiaNombre("LEA2")
                .correlativas(new HashSet<>())
                .build();

        ingles = Materia.builder()
                .materiaNombre("ingles")
                .build();

        leaGuardada = materiaService.guardar(lea);

        Set<Materia> correlativas = new HashSet<>();
        correlativas.add(leaGuardada);

        ingles.setCorrelativas(correlativas);

        inglesGuardada = materiaService.guardar(ingles);

        leaGuardada2 = materiaService.guardar(lea2);
    }

    @Test
    public void crearMateriaConCorrelativas(){


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

        materiaService.asociarMateria(leaGuardada2.getMateriaId(), leaGuardada.getMateriaId());

        Materia leaRecuperada = materiaService.recuperar(lea.getMateriaId());
        Materia leaRecuperada2 = materiaService.recuperar(lea2.getMateriaId());

        boolean hasLea = leaRecuperada2.getCorrelativas().stream()
                .anyMatch(m -> m.getMateriaId().equals(leaRecuperada.getMateriaId()));
        assertTrue(hasLea, "LEA2 debe tener LEA como correlativa");
    }

    @Test
    public void  debeRetornarMateriasDisponiblesSegunAprobadas(){
        List<Materia> materiasAprobadas = new ArrayList<>(List.of(leaGuardada));

        List<Materia> materiasPendientes = materiaService.recuperarMateriasDisponibles(materiasAprobadas);

        Set<Long> idsPendientes = materiasPendientes.stream()
                .map(Materia::getMateriaId)
                .collect(Collectors.toSet());

        Set<Long> idsAprobadas = materiasAprobadas.stream()
                .map(Materia::getMateriaId)
                .collect(Collectors.toSet());

        // Verificar que leaGuardada NO está en pendientes
        assertFalse(idsPendientes.contains(leaGuardada.getMateriaId()));

        // Verificar que ninguna aprobada está en pendientes
        assertTrue(Collections.disjoint(idsAprobadas, idsPendientes));
    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}