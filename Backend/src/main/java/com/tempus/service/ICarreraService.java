package com.tempus.service;

import com.tempus.dto.carrera.CarreraPostDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICarreraService {
    public CarreraPostDTO createdCarrera(CarreraPostDTO dto);
}
