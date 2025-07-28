package com.tempus.controller;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.service.ICarreraService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private ICarreraService carreraService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarrera(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(carreraService.getCarrera(id));
    }

    @GetMapping
    public ResponseEntity<?> getCarreras(){
        return ResponseEntity.status(HttpStatus.OK).body(carreraService.getCarreras());
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<?> getMateriasByCarrera(@PathVariable Long id) {
        return ResponseEntity.ok(carreraService.getMaterias(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCarrera(@PathVariable Long id, @RequestBody CarreraPostDTO carreraPostDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carreraService.putCarrera(id, carreraPostDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable Long id){
        carreraService.deleteCarrera(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino con exito");
    }

    @PostMapping("/crear")
    public ResponseEntity<?> createdCarrera(@RequestBody CarreraPostDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.createdCarrera(dto));
    }
}
