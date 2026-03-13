package edu.ar.tempus.service;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.DiasSemana;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.ComisionNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional("transactionManager")
@Rollback
public class ComisionServiceTest {

    @Autowired
    private ResetService resetService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionNeo4JDAO comisionNeo4JDAO;

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
    @Test
    public void recuperarComisionesPaginadas() {
        ClaseHorario lunes = ClaseHorario.builder()
                .dia(DiasSemana.LUNES)
                .inicio(LocalTime.of(8, 0))
                .fin(LocalTime.of(10, 0))
                .build();
        
        for (int i = 0; i < 12; i++) {
            Comision comision = Comision.builder()
                    .clases(List.of(lunes))
                    .build();
            comisionService.guardar(comision, inglesGuardada.getMateriaId());
        }

        Page<Comision> primeraPagina = comisionService.recuperarComisiones(0);
        Page<Comision> segundaPagina = comisionService.recuperarComisiones(1);

        assertEquals(9, primeraPagina.getContent().size(), "La primera página debería tener 9 comisiones");
        assertEquals(3, segundaPagina.getContent().size(), "La segunda página debería tener 3 comisiones");
        assertEquals(12, primeraPagina.getTotalElements(), "El total de comisiones debería ser 12");
        assertEquals(2, primeraPagina.getTotalPages(), "Debería haber 2 páginas en total");
        assertTrue(primeraPagina.hasNext(), "La primera página debería tener una siguiente");
        assertFalse(segundaPagina.hasNext(), "La segunda página no debería tener una siguiente");
    }

    @Test
    public void crearComisionesYValidarCompatibilidadEnNeo4j() {

        ClaseHorario lunesA = ClaseHorario.builder()
                .dia(DiasSemana.LUNES)
                .inicio(LocalTime.of(8, 0))
                .fin(LocalTime.of(10, 0))
                .build();
        Comision comisionA = Comision.builder()
                .clases(List.of(lunesA))
                .build();
        Comision guardadaA = comisionService.guardar(comisionA, inglesGuardada.getMateriaId());

        ClaseHorario lunesB = ClaseHorario.builder()
                .dia(DiasSemana.LUNES)
                .inicio(LocalTime.of(10, 0))
                .fin(LocalTime.of(12, 0))
                .build();
        Comision comisionB = Comision.builder()
                .clases(List.of(lunesB))
                .build();
        Comision guardadaB = comisionService.guardar(comisionB, inglesGuardada.getMateriaId());

        ClaseHorario lunesC = ClaseHorario.builder()
                .dia(DiasSemana.LUNES)
                .inicio(LocalTime.of(9, 0))
                .fin(LocalTime.of(11, 0))
                .build();
        Comision comisionC = Comision.builder()
                .clases(List.of(lunesC))
                .build();
        Comision guardadaC = comisionService.guardar(comisionC, inglesGuardada.getMateriaId());

        boolean compatibleAB = comisionNeo4JDAO.verificarCompatibilidad(
                guardadaA.getComisionId(),
                guardadaB.getComisionId()
        );
        assertEquals(true, compatibleAB, "La Comisión B debería ser compatible con A (no hay solapamiento)");

        boolean compatibleAC = comisionNeo4JDAO.verificarCompatibilidad(
                guardadaA.getComisionId(),
                guardadaC.getComisionId()
        );
        assertEquals(false, compatibleAC, "La Comisión C NO debería ser compatible con A (se solapan)");

        boolean compatibleBC = comisionNeo4JDAO.verificarCompatibilidad(
                guardadaB.getComisionId(),
                guardadaC.getComisionId()
        );
        assertEquals(false, compatibleBC, "La Comisión B NO debería ser compatible con C (se solapan)");
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}