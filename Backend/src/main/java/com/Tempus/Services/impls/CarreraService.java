package com.Tempus.Services.impls;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Models.Carrera;
import com.Tempus.Repository.ICarreraRepository;
import com.Tempus.Services.ICarreraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<MateriaDTO> findMateriasOfCarreraById(long idCarrera) {
        return this.findCarreraById(idCarrera).getMaterias();
    }

    @Override
    public CarreraDTO findCarreraById(Long idCarrera) {
        return this.toDTO(this.findById(idCarrera));
    }

    private CarreraDTO toDTO(Carrera carrera) {
        return modelMapper.map(carrera, CarreraDTO.class);
    }

    private Carrera findById(Long idCarrera) {
        return carreraRepository.findById(idCarrera).orElseThrow(());
    }

    @Override
    public List<CarreraDTO> getCarreras() {
        return List.of();
    }
}
