package com.Tempus.Services.impls;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.impls.ComisionFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Repository.IComisionRepository;
import com.Tempus.Services.IComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService implements IComisionService {

    @Autowired
    private IComisionRepository comisionRepository;

    @Autowired
    private ComisionFactory comisionFactory;

    @Override
    public ComisionCreatedDTO createdComision(ComisionCreatedDTO comisionDTO) {
        Comision comision = this.save(comisionDTO);

        return comisionFactory.toCreatedDTO(comision);
    }

    @Override
    public List<ComisionDTO> getComisiones() {
        return this.findAll().stream().map(this::toDTO).toList();
    }

    private ComisionDTO toDTO(Comision comision) {
        return comisionFactory.toDTO(comision);
    }

    @Override
    public ComisionDTO getComision(Long id) {
        return toDTO(this.findByIdComision(id));
    }

    @Override
    public ComisionCreatedDTO putComision(Long id, ComisionCreatedDTO comisionCreatedDTO) {
        Comision comision = this.findByIdComision(id);
        comisionFactory.updateEntityFromDTO(comisionCreatedDTO, comision);
        return comisionFactory.toCreatedDTO(comisionRepository.save(comision));
    }

    @Override
    public void deleteComision(Long id) {
        comisionRepository.delete(findByIdComision(id));
    }

    private Comision findByIdComision(Long id) {
        return comisionRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFound("No se encontro la materia")
                );
    }

    private List<Comision> findAll() {
        return comisionRepository.findAll();
    }

    private Comision save(ComisionCreatedDTO comisionDTO) {
        return comisionRepository.save(comisionFactory.toEntity(comisionDTO));
    }

}
