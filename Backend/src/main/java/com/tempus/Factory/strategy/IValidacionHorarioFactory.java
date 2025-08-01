package com.tempus.Factory.strategy;

import com.tempus.strategy.horario.ValidacionHorarioStrategy;

import java.time.LocalTime;

public interface IValidacionHorarioFactory {

    public ValidacionHorarioStrategy obtenerStrategy(LocalTime hora);
}
