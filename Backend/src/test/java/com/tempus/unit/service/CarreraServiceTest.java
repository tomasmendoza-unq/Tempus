package com.tempus.unit.service;

import com.tempus.Factory.models.ICarreraFactory;
import com.tempus.Factory.models.IMateriaFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.data.IMateriaFinder;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.models.Materia;
import com.tempus.repository.ICarreraRepository;
import com.tempus.service.impls.CarreraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    CarreraResponseDTO carreraResponseDTO;

    @Mock
    ICarreraFactory carreraFactory;

    @Mock
    CarreraPostDTO carreraPostDTO;

    @Mock
    ICarreraRepository carreraRepository;

    @Mock
    Carrera saved;

    @Mock
    Carrera carrera;

    @Mock
    IEntityFinder<Carrera> carreraFinder;

    @InjectMocks
    CarreraService carreraService;

    @Mock
    IMateriaFinder materiaFinder;

    @Mock
    IMateriaFactory materiaFactory;

    @Mock
    Materia materia;

    @Mock
    MateriaResponseDTO materiaResponseDTO;

    Long id ;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testCreatedCarreraOk(){
        when(carreraFactory.toEntity(carreraPostDTO)).thenReturn(carrera);
        when(carreraRepository.save(carrera)).thenReturn(saved);
        when(carreraFactory.toResponseDTO(saved)).thenReturn(carreraResponseDTO);

        carreraService.createdCarrera(carreraPostDTO);

        verify(carreraFactory).toEntity(carreraPostDTO);
        verify(carreraRepository).save(carrera);
        verify(carreraFactory).toResponseDTO(saved);
    }

    @Test
    public void testGetCarreraOk(){
        when(carreraFactory.toResponseDTO(carrera)).thenReturn(carreraResponseDTO);
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.getCarrera(id);

        verify(carreraFactory).toResponseDTO(carrera);
        verify(carreraFinder).findById(id);
    }

    @Test
    public void testGetCarrerasOk(){
        when(carreraFactory.toResponseDTO(carrera)).thenReturn(carreraResponseDTO);
        when(carreraRepository.findAll()).thenReturn(List.of(carrera));

        carreraService.getCarreras();

        verify(carreraFactory).toResponseDTO(carrera);
        verify(carreraRepository).findAll();
    }

    @Test
    public void testPutCarreraOk(){
        when(carreraFactory.toResponseDTO(saved)).thenReturn(carreraResponseDTO);
        when(carreraRepository.save(carrera)).thenReturn(saved);
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.putCarrera(id, carreraPostDTO);

        verify(carreraFactory).toResponseDTO(saved);
        verify(carreraRepository).save(carrera);
        verify(carreraFinder).findById(id);
    }

    @Test
    public void testDeleteCarreraOk(){
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.deleteCarrera(id);

        verify(carreraFinder).findById(id);
    }

    @Test
    public void testGetMateriasOk(){
        List<Materia> materias = List.of(materia);
        when(materiaFinder.findMateriasByCarreraId(id)).thenReturn(materias);
        when(materiaFactory.toResponseDTO(materia)).thenReturn(materiaResponseDTO);

        carreraService.getMaterias(id);

        verify(materiaFinder).findMateriasByCarreraId(id);
        verify(materiaFactory).toResponseDTO(materia);
    }
}
