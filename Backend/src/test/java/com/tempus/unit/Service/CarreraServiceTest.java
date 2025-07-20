package com.tempus.unit.Service;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaResumenDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.ICarreraRepository;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.impls.CarreraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    private ICarreraRepository carreraRepository;

    @Mock
    private IMateriaRepository materiaRepository;

    @InjectMocks
    private CarreraService carreraService;

    @Mock
    private Carrera carrera;

    @Mock
    private CarreraDTO carreraDTO;

    @Mock
    private MateriaResumenDTO materiaDTO;

    private Set<MateriaResumenDTO> materiaResumenDTOS;

    private List<Carrera> carreras;



    @BeforeEach
    public void setUp(){
        carreras = List.of(carrera);
        materiaResumenDTOS = Set.of(materiaDTO);
    }

    @Test
    public void findCarreraByIdTestOK(){
        when(carreraRepository.findById(1L)).thenReturn(Optional.of(carrera));
        when(carrera.getNombre()).thenReturn("Matematica");
        when(carrera.getId_carrera()).thenReturn(1L);
        when(materiaRepository.buscarMateriasPorCarrera(1L)).thenReturn(materiaResumenDTOS);


        carreraService.findCarreraById(1L);

        verify(carreraRepository).findById(1L);
        verify(materiaRepository).buscarMateriasPorCarrera(1L);
    }

    @Test
    public void findCarreraByIdTestIsFailed(){
        when(carreraRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> carreraService.findCarreraById(1L));
    }

    @Test
    public void findMateriasOfCarreraByIdTestOK(){
        when(carreraRepository.findById(1L)).thenReturn(Optional.of(carrera));
        when(carrera.getNombre()).thenReturn("Matematica");
        when(carrera.getId_carrera()).thenReturn(1L);
        when(materiaRepository.buscarMateriasPorCarrera(1L)).thenReturn(materiaResumenDTOS);



        carreraService.findMateriasOfCarreraById(1L);

        verify(carreraRepository).findById(1L);
        verify(materiaRepository).buscarMateriasPorCarrera(1L);
    }

    @Test
    public void findMateriasOfCarreraByIdTestIsFailed(){
        when(carreraRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> carreraService.findCarreraById(1L));
    }

    @Test
    public void getCarrerasTestOK(){
        when(carreraRepository.findAll()).thenReturn(carreras);
        when(carrera.getNombre()).thenReturn("Matematica");
        when(carrera.getId_carrera()).thenReturn(1L);
        when(materiaRepository.buscarMateriasPorCarrera(1L)).thenReturn(materiaResumenDTOS);



        carreraService.getCarreras();

        verify(carreraRepository).findAll();
        verify(materiaRepository).buscarMateriasPorCarrera(1L);
    }


    @Test
    public void createdCarreraOK(){
        when(carreraDTO.getNombre()).thenReturn("Matematica");

        Carrera carreraMock = Carrera.builder()
                .nombre("Matematica")
                .build();

        when(carreraRepository.save(any(Carrera.class))).thenReturn(carreraMock);

        carreraService.createdCarrera(carreraDTO);

        verify(carreraRepository).save(any(Carrera.class));
    }
}
