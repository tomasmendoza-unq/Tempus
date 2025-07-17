package com.tempus.unit.Service;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.ICarreraRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    private ICarreraRepository carreraRepository;

    @InjectMocks
    private CarreraService carreraService;

    @Mock
    private Carrera carrera;

    @Mock
    private MateriaDTO materiaDTO;

    @Mock
    private Materia materia;

    private List<Carrera> carreras;

    private Set<Materia> materias;

    @BeforeEach
    public void setUp(){
        carreras = List.of(carrera);
        materias = Set.of(materia);

    }

    @Test
    public void findCarreraByIdTestOK(){
        when(carreraRepository.findById(1L)).thenReturn(Optional.of(carrera));
        when(carrera.getNombre()).thenReturn("Matematica");
        when(carrera.getMaterias()).thenReturn(materias);
        when(materia.toDTO()).thenReturn(materiaDTO);

        carreraService.findCarreraById(1L);

        verify(carreraRepository).findById(1L);
        verify(carrera).getNombre();
        verify(carrera).getMaterias();
        verify(materia).toDTO();

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
        when(carrera.getMaterias()).thenReturn(materias);
        when(materia.toDTO()).thenReturn(materiaDTO);

        carreraService.findMateriasOfCarreraById(1L);

        verify(carreraRepository).findById(1L);
        verify(carrera).getNombre();
        verify(carrera).getMaterias();
        verify(materia).toDTO();
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
        when(carrera.getMaterias()).thenReturn(materias);
        when(materia.toDTO()).thenReturn(materiaDTO);

        carreraService.getCarreras();

        verify(carreraRepository).findAll();
        verify(carrera).getNombre();
        verify(carrera).getMaterias();
        verify(materia).toDTO();
    }

}
