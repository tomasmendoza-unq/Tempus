package com.tempus.Factory.strategy.imps;

import com.tempus.Factory.strategy.IValidacionHorarioFactory;
import com.tempus.strategy.horario.ValidacionHorarioStrategy;
import com.tempus.strategy.horario.impls.ValidacionMañana;
import com.tempus.strategy.horario.impls.ValidacionNoche;
import com.tempus.strategy.horario.impls.ValidacionTarde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ValidacionHorarioFactory implements IValidacionHorarioFactory {
    private static final List<ValidacionHorarioStrategy> estrategias = List.of(
            new ValidacionMañana(),
            new ValidacionTarde(),
            new ValidacionNoche()
    );

    public ValidacionHorarioStrategy obtenerStrategy(LocalTime hora){
        return estrategias
                .stream()
                .filter(e -> e.aplicaPara(hora))
                .findFirst()
                .get();
    }
}
