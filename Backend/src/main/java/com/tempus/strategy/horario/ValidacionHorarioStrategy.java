package com.tempus.strategy.horario;

import com.tempus.enums.Turno;

import java.time.LocalTime;

public abstract class  ValidacionHorarioStrategy {

    public abstract boolean aplicaPara(LocalTime hora);

    public boolean horarioPermitido(LocalTime inicio, LocalTime fin) {
        return aplicaPara(inicio) && aplicaPara(fin);
    }

    public LocalTime horaMañana(){
        return Turno.MAÑANA.getInicio();
    }

    public LocalTime horaTarde(){
        return Turno.TARDE.getInicio();
    }

    public LocalTime horaNoche(){
        return Turno.NOCHE.getInicio();
    }
}
