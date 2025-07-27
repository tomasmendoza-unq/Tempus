package com.tempus.controller;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private ICarreraService carreraService;

    @PostMapping("/crear")
    public ResponseEntity<?> createdCarrera(@RequestBody CarreraPostDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.createdCarrera(dto));
    }
}
