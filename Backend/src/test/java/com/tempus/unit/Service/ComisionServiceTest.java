package com.tempus.unit.Service;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.impls.ComisionFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IComisionRepository;
import com.Tempus.Services.IMateriaService;
import com.Tempus.Services.impls.ComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComisionServiceTest {
    @Mock
    private IComisionRepository comisionRepository;

    @Mock
    private ComisionFactory comisionFactory;

    @InjectMocks
    private ComisionService comisionService;

    @Mock
    private ComisionCreatedDTO comisionCreatedDTO;

    @Mock
    private Comision comision;

    @Mock
    private Comision comision2;

    @Mock
    private Comision savedComision;

    @Mock
    private ComisionCreatedDTO responseDTO;

    @Mock
    private ComisionDTO comisionDTO;

    @Mock
    private ComisionDTO comisionDTO2;

    @Test
    public void testCreatedComisionOK(){
        when(comisionFactory.toEntity(comisionCreatedDTO)).thenReturn(comision);
        when(comisionFactory.toCreatedDTO(savedComision)).thenReturn(responseDTO);
        when(comisionRepository.save(comision)).thenReturn(savedComision);

        comisionService.createdComision(comisionCreatedDTO);

        verify(comisionRepository).save(comision);
        verify(comisionFactory).toCreatedDTO(savedComision);
        verify(comisionFactory).toEntity(comisionCreatedDTO);
    }

    @Test
    public void testGetComisionesOk(){
        when(comisionRepository.findAll()).thenReturn(List.of(comision,comision2));
        when(comisionFactory.toDTO(comision)).thenReturn(comisionDTO);
        when(comisionFactory.toDTO(comision2)).thenReturn(comisionDTO2);

        comisionService.getComisiones();

        verify(comisionRepository).findAll();
        verify(comisionFactory).toDTO(comision);
        verify(comisionFactory).toDTO(comision2);

    }

    @Test
    public void testGetComisionOk(){
        when(comisionFactory.toDTO(comision)).thenReturn(comisionDTO);
        when(comisionRepository.findById(1L)).thenReturn(Optional.of(comision));

        comisionService.getComision(1L);

        verify(comisionFactory).toDTO(comision);
        verify(comisionRepository).findById(1L);
    }
    @Test
    public void testGetComisionFailed(){
        when(comisionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class , () -> comisionService.getComision(1L));

    }

    @Test
    public void testPutComisionOk(){
        Long id = 1L;

        when(comisionRepository.findById(id)).thenReturn(Optional.of(comision));

        when(comisionRepository.save(comision)).thenReturn(comision2);

        when(comisionFactory.toCreatedDTO(comision2)).thenReturn(responseDTO);

        ComisionCreatedDTO result = comisionService.putComision(id, comisionCreatedDTO);

        verify(comisionRepository).findById(id);
        verify(comisionFactory).updateEntityFromDTO(comisionCreatedDTO, comision);
        verify(comisionRepository).save(comision);
        verify(comisionFactory).toCreatedDTO(comision2);
    }

    @Test
    public void testDeleteComisionOk(){
        Long id = 1L;

        when(comisionRepository.findById(id)).thenReturn(Optional.of(comision));

        comisionService.deleteComision(id);

        verify(comisionRepository).delete(comision);
        verify(comisionRepository).findById(id);
    }

    @Test
    public void testDeleteComisionFailed(){
        Long id = 1L;

        when(comisionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class , () -> comisionService.deleteComision(id));
    }
}
