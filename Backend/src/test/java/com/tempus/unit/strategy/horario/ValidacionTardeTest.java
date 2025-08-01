package com.tempus.unit.strategy.horario;

import com.tempus.strategy.horario.impls.ValidacionNoche;
import com.tempus.strategy.horario.impls.ValidacionTarde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacionTardeTest {
    ValidacionTarde validacionTarde;

    @BeforeEach
    public void setUp(){
        validacionTarde = new ValidacionTarde();
    }

    @Test
    public void testAplicaElTurnoTarde(){
        LocalTime horaValida = LocalTime.of(15, 0);
        boolean resultado = validacionTarde.aplicaPara(horaValida);

        assertTrue(resultado);
    }

    @Test
    public void testNoAplicaElTurnoTarde(){
        LocalTime horaValida = LocalTime.of(22, 0);
        boolean resultado = validacionTarde.aplicaPara(horaValida);

        assertFalse(resultado);
    }


}
