package com.tempus.strategy.horario.impls;

import com.tempus.enums.Turno;
import com.tempus.strategy.horario.ValidacionHorarioStrategy;

import java.time.LocalTime;

public class ValidacionNoche extends ValidacionHorarioStrategy {
    @Override
    public boolean aplicaPara(LocalTime hora) {
        return !hora.isBefore(horaNoche()) || hora.isBefore(horaMañana());
    }
}
