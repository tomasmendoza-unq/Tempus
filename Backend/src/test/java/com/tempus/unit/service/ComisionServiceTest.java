package com.tempus.unit.service;

import com.tempus.Factory.models.IComisionFactory;
import com.tempus.Factory.strategy.IValidacionHorarioFactory;
import com.tempus.data.IComisionFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.enums.Turno;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import com.tempus.service.impls.ComisionService;
import com.tempus.strategy.horario.ValidacionHorarioStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComisionServiceTest {

    @Mock
    IComisionFactory comisionFactory;

    @InjectMocks
    ComisionService comisionService;

    @Mock
    IComisionFinder finderComision;

    @Mock
    IComisionRepository comisionRepository;

    @Mock
    Comision comision;
    @Mock
    Comision comision2;

    @Mock
    Comision saved;

    @Mock
    ComisionPostDTO comisionPostDTO;

    @Mock
    ComisionPostDTO responseSaved;

    @Mock
    ComisionResponseDTO comisionResponseDTO;

    @Mock
    ComisionResponseDTO comisionResponseDTO2;

    @Mock
    IValidacionHorarioFactory validacionHorarioFactory;

    @Mock
    ValidacionHorarioStrategy validacionHorarioStrategy;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testCreatedComisionOk(){
        when(comisionRepository.save(comision)).thenReturn(saved);
        when(comisionFactory.toEntity(comisionPostDTO)).thenReturn(comision);
        when(comisionFactory.toPostDTO(saved)).thenReturn(responseSaved);

        comisionService.createdComision(comisionPostDTO);

        verify(comisionRepository).save(comision);
        verify(comisionFactory).toEntity(comisionPostDTO);
        verify(comisionFactory).toPostDTO(saved);


    }

    @Test
    public void testGetComisionOk(){
        when(comisionFactory.toResponseDTO(comision)).thenReturn(comisionResponseDTO);
        when(finderComision.findComisionWithMateria(id)).thenReturn(comision);

        comisionService.getComision(id);

        verify(comisionFactory).toResponseDTO(comision);
        verify(finderComision).findComisionWithMateria(id);
    }

    @Test
    public void testGetComisionesOk(){
        List<Comision> comisiones = List.of(comision);

        when(comisionFactory.toResponseDTO(comision)).thenReturn(comisionResponseDTO);
        when(finderComision.findAll()).thenReturn(comisiones);

        comisionService.getComisiones();

        verify(comisionFactory).toResponseDTO(comision);
        verify(finderComision).findAll();

    }

    @Test
    public void testPutComisionOk(){
        when(finderComision.findById(id)).thenReturn(comision);
        when(comisionRepository.save(comision)).thenReturn(saved);
        when(comisionFactory.toPostDTO(saved)).thenReturn(comisionPostDTO);

        comisionService.putComision(comisionPostDTO, id);

        verify(finderComision).findById(id);
        verify(comisionRepository).save(comision);
        verify(comisionFactory).toPostDTO(saved);

    }

    @Test
    public void testDeleteComisionOk(){
        when(finderComision.findById(id)).thenReturn(comision);

        comisionService.deleteComision(id);

        verify(finderComision).findById(id);
        verify(comisionRepository).delete(comision);
    }

    @Test
    public void testGetComisionesPorTurnoOk(){
        List<Comision> comisiones = List.of(comision, comision2);
        Turno turno = Turno.MAÑANA;

        when(comisionFactory.toResponseDTO(comision)).thenReturn(comisionResponseDTO);;
        when(finderComision.findAll()).thenReturn(comisiones);
        when(comision.getHorario()).thenReturn(LocalTime.of(0, 0)); // Hora irrelevante para este test
        when(comision2.getHorario()).thenReturn(LocalTime.of(1, 1)); // Hora irrelevante para este test
        when(validacionHorarioFactory.obtenerStrategy(turno.getInicio())).thenReturn(validacionHorarioStrategy);
        when(validacionHorarioStrategy.aplicaPara(LocalTime.of(0, 0))).thenReturn(true);
        when(validacionHorarioStrategy.aplicaPara(LocalTime.of(1, 1))).thenReturn(false);

        List<ComisionResponseDTO> comisionResponseDTOS = comisionService.getComisionesPorHorario(turno);

        verify(comisionFactory).toResponseDTO(comision);
        verify(finderComision).findAll();
        verify(comision).getHorario();
        verify(comision2).getHorario();
        verify(validacionHorarioFactory).obtenerStrategy(turno.getInicio());
        verify(validacionHorarioStrategy).aplicaPara(LocalTime.of(0, 0));
        verify(validacionHorarioStrategy).aplicaPara(LocalTime.of(0, 0));
        verify(comisionFactory, never()).toResponseDTO(comision2);
    }
}
