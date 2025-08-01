package com.tempus.unit.factory.strategy;

import com.tempus.Factory.strategy.imps.ValidacionHorarioFactory;
import com.tempus.strategy.horario.ValidacionHorarioStrategy;
import com.tempus.strategy.horario.impls.ValidacionMañana;
import com.tempus.strategy.horario.impls.ValidacionNoche;
import com.tempus.strategy.horario.impls.ValidacionTarde;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidacionHorarioFactoryTest {

    private final ValidacionHorarioFactory selector = new ValidacionHorarioFactory();

    @Test
    public void devuelveValidacionMañanaParaHorarioMatutino() {
        LocalTime hora = LocalTime.of(8, 30);
        ValidacionHorarioStrategy estrategia = selector.obtenerStrategy(hora);
        assertInstanceOf(ValidacionMañana.class, estrategia);
    }

    @Test
    public void devuelveValidacionTardeParaHorarioVespertino() {
        LocalTime hora = LocalTime.of(15, 0);
        ValidacionHorarioStrategy estrategia = selector.obtenerStrategy(hora);
        assertInstanceOf(ValidacionTarde.class, estrategia);
    }

    @Test
    public void devuelveValidacionNocheParaHorarioNocturno() {
        LocalTime hora = LocalTime.of(20, 0);
        ValidacionHorarioStrategy estrategia = selector.obtenerStrategy(hora);
        assertInstanceOf(ValidacionNoche.class, estrategia);
    }



}
