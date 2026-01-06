package edu.ar.tempus.service;

import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional("transactionManager")
public class CarreraServiceTest {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ResetService resetService;

    @Autowired
    private CarreraService carreraService;

    private Materia lea;
    private Materia leaGuardada;

    private Carrera carrera;

    @BeforeEach
    public void setUp() {
        lea = Materia.builder()
                .materiaNombre("LEA")
                .correlativas(new HashSet<>())
                .build();

        leaGuardada = materiaService.guardar(lea);

        carrera = Carrera.builder()
                .nombreCarrera("Lic. en informatica")
                .build();
    }

    @Test
    public void crearUnaCarreraYRecuperarla(){
        Carrera carreraGuardada = carreraService.guardar(carrera, Set.of(leaGuardada.getMateriaId()));

        Carrera carreraRecuperada = carreraService.recuperar(carreraGuardada.getId());

        assertEquals(carreraGuardada.getNombreCarrera(), carreraRecuperada.getNombreCarrera());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
