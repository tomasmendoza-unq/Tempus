package com.tempus.unit.strategy.horario;


import com.tempus.strategy.horario.impls.ValidacionMañana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class ValidacionMañanaTest {

    ValidacionMañana validacionMañana;

    @BeforeEach
    public void setUp(){
        validacionMañana = new ValidacionMañana();
    }

    @Test
    public void testAplicaElTurnoMañana(){
        LocalTime horaValida = LocalTime.of(7, 0);
        boolean resultado = validacionMañana.aplicaPara(horaValida);

        assertTrue(resultado);
    }

    @Test
    public void testNoAplicaElTurnoMañana(){
        LocalTime horaValida = LocalTime.of(15, 0);
        boolean resultado = validacionMañana.aplicaPara(horaValida);

        assertFalse(resultado);
    }
}
