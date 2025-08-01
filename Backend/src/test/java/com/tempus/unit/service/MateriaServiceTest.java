package com.tempus.unit.service;

import com.tempus.Factory.models.IMateriaFactory;
import com.tempus.data.IMateriaFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.models.Materia;
import com.tempus.repository.IMateriaRepository;
import com.tempus.service.impls.MateriaService;
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
public class MateriaServiceTest {


    @InjectMocks
    MateriaService materiaService;

    @Mock
    IMateriaRepository materiaRepository;

    @Mock
    IMateriaFactory materiaFactory;

    @Mock
    IMateriaFinder finderMateria;

    @Mock
    Materia materia;

    @Mock
    Materia saved;

    @Mock
    MateriaPostDTO materiaPostDTO;

    @Mock
    MateriaResponseDTO materiaResponseDTO;

    @Mock
    MateriaSimpleDTO materiaSimpleDTO;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testGetMateriaOk(){
        when(finderMateria.findMateriaWithCorrelativas(id)).thenReturn(materia);
        when(materiaFactory.toResponseDTO(materia)).thenReturn(materiaResponseDTO);

        materiaService.getMateria(id);

        verify(materiaFactory).toResponseDTO(materia);
        verify(finderMateria).findMateriaWithCorrelativas(id);

    }

    @Test
    public void testGetMateriasOk(){
        List<Materia> materias = List.of(materia);
        when(finderMateria.findAll()).thenReturn(materias);
        when(materiaFactory.toSimpleDTO(materia)).thenReturn(materiaSimpleDTO);

        materiaService.getMaterias();

        verify(finderMateria).findAll();
        verify(materiaFactory).toSimpleDTO(materia);
    }

    @Test
    public void testCreatedMateriaOk(){
        when(materiaRepository.save(materia)).thenReturn(saved);
        when(materiaFactory.toEntity(materiaPostDTO)).thenReturn(materia);
        when(materiaFactory.toSimpleDTO(saved)).thenReturn(materiaSimpleDTO);

        materiaService.createdMateria(materiaPostDTO);

        verify(materiaFactory).toSimpleDTO(saved);
        verify(materiaFactory).toEntity(materiaPostDTO);
        verify(materiaRepository).save(materia);
    }

    @Test
    public void testPutMateriaOK(){
        when(materiaRepository.save(materia)).thenReturn(saved);
        when(finderMateria.findById(id)).thenReturn(materia);
        when(materiaFactory.toSimpleDTO(saved)).thenReturn(materiaSimpleDTO);

        materiaService.putMateria(materiaPostDTO, id);

        verify(materiaFactory).toSimpleDTO(saved);
        verify(finderMateria).findById(id);
        verify(materiaRepository).save(materia);
    }

    @Test
    public void testDeleteMateriaOk(){
        when(finderMateria.findById(id)).thenReturn(materia);

        materiaService.deleteMateria(id);

        verify(finderMateria).findById(id);
    }


}
