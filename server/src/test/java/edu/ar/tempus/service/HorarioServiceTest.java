package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Horario;
import edu.ar.tempus.model.Materia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HorarioServiceTest {


    @Autowired
    private HorarioService horarioService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ComisionService comisionService;

    @Autowired
    private ResetService resetService;

    private Materia lea;
    private Materia lea2;
    private Materia lea3;

    // Comisiones sin superposición
    private Comision leaManana;      // 08:00 - 10:00
    private Comision lea2Tarde;      // 14:00 - 16:00
    private Comision lea3Noche;      // 18:00 - 20:00

    // Comisiones con superposición
    private Comision leaTarde;       // 14:00 - 16:00  <- choca con lea2Tarde

    @BeforeEach
    public void setUp() {
        lea = materiaService.guardar(Materia.builder()
                .materiaNombre("LEA")
                .correlativas(new HashSet<>())
                .build());

        lea2 = materiaService.guardar(Materia.builder()
                .materiaNombre("LEA2")
                .correlativas(new HashSet<>())
                .build());

        lea3 = materiaService.guardar(Materia.builder()
                .materiaNombre("lea3")
                .correlativas(new HashSet<>(Set.of(lea2)))
                .build());

        // Sin superposición
        leaManana    = comisionService.guardar(Comision.builder().horarioInicio(LocalTime.of(8,  0)).horarioFin(LocalTime.of(10, 0)).build(), lea.getMateriaId());
        lea2Tarde    = comisionService.guardar(Comision.builder().horarioInicio(LocalTime.of(14, 0)).horarioFin(LocalTime.of(16, 0)).build(), lea2.getMateriaId());
        lea3Noche    = comisionService.guardar(Comision.builder().horarioInicio(LocalTime.of(18, 0)).horarioFin(LocalTime.of(20, 0)).build(), lea3.getMateriaId());

        // Con superposición
        leaTarde = comisionService.guardar(Comision.builder().horarioInicio(LocalTime.of(14, 0)).horarioFin(LocalTime.of(16, 0)).build(), lea.getMateriaId());
    }

    //todas las combinaciones entre una lista de materias donde no se superponen
    // (osea se descarta una materia cuando se superpone con otra)
    @Test
    public void seGeneraUnSoloHorarioParaLea123() {

        List<Long> materiasIds = new ArrayList<>(
                List.of(
                        lea.getMateriaId(),
                        lea2.getMateriaId(),
                        lea3.getMateriaId()
                )
        );

        Optional<Horario> horarioOpt = horarioService.generarUnHorarioCon(materiasIds);

        // Debe existir un horario válido
        assertTrue(horarioOpt.isPresent());

        Horario horario = horarioOpt.get();

        // Debe tener 3 comisiones
        assertEquals(3, horario.getComisiones().size());

        // Debe contener la comisión de la mañana (08:00)
        assertTrue(
                horario.getComisiones().stream()
                        .anyMatch(c -> c.getHorarioInicio().equals(LocalTime.of(8, 0)))
        );

        // NO debe contener la comisión conflictiva de LEA (14:00)
        assertFalse(
                horario.getComisiones().stream()
                        .anyMatch(c ->
                                c.getHorarioInicio().equals(LocalTime.of(14, 0)) &&
                                        c.getMateria().getMateriaId().equals(lea.getMateriaId())
                        )
        );
    }
    @Test
    public void seGeneranHorariosParaLea123() {

        List<Long> materiasIds = new ArrayList<>(
                List.of(
                        lea.getMateriaId(),
                        lea2.getMateriaId(),
                        lea3.getMateriaId()
                )
        );

        List<Horario> horarios = horarioService.generarHorariosCon(materiasIds);

        // Debe existir solo 1 combinación válida
        assertEquals(1, horarios.size());

        Horario horario = horarios.get(0);

        // Debe tener 3 comisiones (una por materia)
        assertEquals(3, horario.getComisiones().size());

        // Verificamos que esté la comisión correcta de LEA (la de la mañana)
        assertTrue(
                horario.getComisiones().stream()
                        .anyMatch(c -> c.getHorarioInicio().equals(LocalTime.of(8, 0)))
        );

        // Verificamos que NO esté la comisión que se superpone
        assertFalse(
                horario.getComisiones().stream()
                        .anyMatch(c -> c.getHorarioInicio().equals(LocalTime.of(14, 0)) &&
                                c.getMateria().getMateriaId().equals(lea.getMateriaId()))
        );
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}