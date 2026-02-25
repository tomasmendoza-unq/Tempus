package edu.ar.tempus.service.impl;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Horario;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.HorarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceimpl implements HorarioService {

    private final ComisionService comisionService;

    public HorarioServiceimpl(ComisionService comisionService) {
        this.comisionService = comisionService;
    }

    @Override
    public List<Horario> generarHorariosCon(List<Long> materiasIds) {
        return null;
    }

    @Override
    public Optional<Horario> generarUnHorarioCon(List<Long> materiasIds) {
        return null;
    }
}
