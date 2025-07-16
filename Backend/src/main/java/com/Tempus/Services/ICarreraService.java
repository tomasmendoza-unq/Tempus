package com.Tempus.Services;

import org.springframework.stereotype.Service;

@Service
public interface ICarreraService {
    Object findMateriasOfCarreraById(long idCarrera);

    Object findCarreraById(Long idCarrera);

    Object getCarreras();
}
