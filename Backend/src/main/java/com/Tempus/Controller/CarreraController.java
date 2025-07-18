package com.Tempus.Controller;

import com.Tempus.Services.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    ICarreraService carreraService;

    @GetMapping
    public ResponseEntity<?> getCarreras(){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.getCarreras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarrera(@PathVariable Long id_carrera){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.findCarreraById(id_carrera));
    }

    @GetMapping("materia/{id}")
    public ResponseEntity<?> getMateriasOfCarrera(@PathVariable long id_carrera){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.findMateriasOfCarreraById(id_carrera));
    }


}
