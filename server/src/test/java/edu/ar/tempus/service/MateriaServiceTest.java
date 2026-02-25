package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
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

    private Materia lea3;

    private Materia leaGuardada;

    private Materia inglesGuardada;

    private Materia leaGuardada2;

    private Materia leaGuardada3;

    @Autowired
    private ComisionService comisionService;

    private Comision comision;


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

        lea3 = Materia.builder()
                .materiaNombre("lea3")
                .build();

        leaGuardada = materiaService.guardar(lea);

        Set<Materia> correlativas = new HashSet<>();
        correlativas.add(leaGuardada);

        ingles.setCorrelativas(correlativas);

        inglesGuardada = materiaService.guardar(ingles);

        leaGuardada2 = materiaService.guardar(lea2);

        lea3.setCorrelativas(new HashSet<>(Set.of(leaGuardada2)));

        leaGuardada3 = materiaService.guardar(lea3);

        LocalTime horarioInicio = LocalTime.of(8,0);
        LocalTime horarioFin = LocalTime.of(10,0);

        comision = Comision.builder()
                .horarioInicio(horarioInicio)
                .horarioFin(horarioFin)
                .build();
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

        materiaService.asociarMateria(leaGuardada.getMateriaId(), leaGuardada2.getMateriaId() );

        Materia leaRecuperada = materiaService.recuperar(lea.getMateriaId());
        Materia leaRecuperada2 = materiaService.recuperar(lea2.getMateriaId());

        boolean hasLea = leaRecuperada2.getCorrelativas().stream()
                .anyMatch(m -> m.getMateriaId().equals(leaRecuperada.getMateriaId()));
        assertTrue(hasLea, "LEA2 debe tener LEA como correlativa");
    }

    //UNA MATERIA ESTA DISPONIBLE SI SE PUEDE CURSAR EN EL PROXIMO CUATRI
    @Test
    public void  debeRetornarMateriasDisponiblesSegunAprobadas(){
        List<Long> idsAprobadas = new ArrayList<>(List.of(leaGuardada.getMateriaId()));

        List<Materia> materiasPendientes = materiaService.recuperarMateriasDisponibles(idsAprobadas);

        Set<Long> idsPendientes = materiasPendientes.stream()
                .map(Materia::getMateriaId)
                .collect(Collectors.toSet());

        assertFalse(idsPendientes.contains(leaGuardada.getMateriaId()));

        assertTrue(Collections.disjoint(idsAprobadas, idsPendientes));
    }


    @Test
    public void recuperarMateriaPorNombre(){

        List<Materia> materias = materiaService.recuperarMateriasPorNombre("Lea");

        assertTrue(materias.stream().allMatch(m -> m.getMateriaNombre().toLowerCase().contains("lea")));
        assertEquals(3, materias.size());

        List<Materia> materia = materiaService.recuperarMateriasPorNombre("Lea2");

        assertTrue(materia.stream().allMatch(m -> m.getMateriaNombre().equalsIgnoreCase("lEA2")));
        assertEquals(1, materia.size());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}