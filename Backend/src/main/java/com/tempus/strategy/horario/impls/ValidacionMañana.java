package com.tempus.strategy.horario.impls;

import com.tempus.strategy.horario.ValidacionHorarioStrategy;

import java.time.LocalTime;

public class ValidacionMañana extends ValidacionHorarioStrategy {
    @Override
    public boolean aplicaPara(LocalTime hora) {
        return !hora.isBefore(horaMañana()) && hora.isBefore(horaTarde());
    }
}
