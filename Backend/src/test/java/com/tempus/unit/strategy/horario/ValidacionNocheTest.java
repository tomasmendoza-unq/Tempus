package com.tempus.unit.strategy.horario;

import com.tempus.strategy.horario.impls.ValidacionMañana;
import com.tempus.strategy.horario.impls.ValidacionNoche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacionNocheTest {
    ValidacionNoche validacionNoche;

    @BeforeEach
    public void setUp(){
        validacionNoche = new ValidacionNoche();
    }

    @Test
    public void testAplicaElTurnoNoche(){
        LocalTime horaValida = LocalTime.of(22, 0);
        boolean resultado = validacionNoche.aplicaPara(horaValida);

        assertTrue(resultado);
    }

    @Test
    public void testNoAplicaElTurnoNoche(){
        LocalTime horaValida = LocalTime.of(15, 0);
        boolean resultado = validacionNoche.aplicaPara(horaValida);

        assertFalse(resultado);
    }

}
