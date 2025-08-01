package com.tempus.service.impls;

import com.tempus.Factory.models.IComisionFactory;
import com.tempus.Factory.strategy.IValidacionHorarioFactory;
import com.tempus.data.IComisionFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.enums.Turno;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import com.tempus.service.IComisionService;
import com.tempus.strategy.horario.ValidacionHorarioStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService implements IComisionService {

    @Autowired
    private IComisionRepository comisionRepository;

    @Autowired
    private IComisionFactory comisionFactory;

    @Autowired
    private IComisionFinder finderComision;

    @Autowired
    private IValidacionHorarioFactory validacionHorarioFactory;

    @Override
    public ComisionPostDTO createdComision(ComisionPostDTO comisionPostDTO) {
        Comision comision = comisionFactory.toEntity(comisionPostDTO);
        Comision saved = this.save(comision);

        return comisionFactory.toPostDTO(saved);
    }

    @Override
    public List<ComisionResponseDTO> getComisiones() {
        return findAll().stream().map(this::toResponseDTO).toList();
    }

    private ComisionResponseDTO toResponseDTO(Comision comision) {
        return comisionFactory.toResponseDTO(comision);
    }

    private List<Comision> findAll() {
        return finderComision.findAll();
    }

    @Override
    public ComisionResponseDTO getComision(Long id) {
        return comisionFactory.toResponseDTO(findComisionWithMateria(id));
    }

    private Comision findComisionWithMateria(Long id) {
        return finderComision.findComisionWithMateria(id);
    }

    private Comision findComision(Long id) {
        return finderComision.findById(id);
    }

    @Override
    public ComisionPostDTO putComision(ComisionPostDTO comisionPostDTO, Long id) {
        Comision comision = this.findComision(id);
        comisionFactory.updateEntity(comision, comisionPostDTO);
        Comision saved = this.save(comision);

        return comisionFactory.toPostDTO(saved);
    }

    @Override
    public void deleteComision(Long id) {
        comisionRepository.delete(findComision(id));
    }

    @Override
    public List<ComisionResponseDTO> getComisionesPorHorario(Turno turno) {
        ValidacionHorarioStrategy strategy = validacionHorarioFactory.obtenerStrategy(turno.getInicio());
        List<Comision> comisiones = findAll()
                .stream()
                .filter(comision -> strategy.aplicaPara(comision.getHorario()))
                .toList();

        return comisiones.stream()
                .map(this::toResponseDTO).toList();
    }

    private Comision save(Comision comision) {
        return comisionRepository.save(comision);
    }


}
