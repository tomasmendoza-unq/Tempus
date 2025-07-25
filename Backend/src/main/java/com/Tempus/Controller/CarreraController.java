package com.Tempus.Controller;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.Services.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    ICarreraService carreraService;

    @GetMapping
    public ResponseEntity<?> getCarreras(){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.getCarreras());
    }

    @GetMapping("/{id_carrera}")
    public ResponseEntity<?> getCarrera(@PathVariable Long id_carrera){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.findCarreraById(id_carrera));
    }

    @GetMapping("materia/{id_carrera}")
    public ResponseEntity<?> getMateriasOfCarrera(@PathVariable Long id_carrera){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.findMateriasOfCarreraById(id_carrera));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> createdCarrera(@RequestBody CarreraDTO carreraDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.createdCarrera(carreraDTO));
    }

}
