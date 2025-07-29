package com.tempus.controller;

import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    IMateriaService materiaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(materiaService.getMateria(id));
    }

    @GetMapping
    public ResponseEntity<?> getMaterias(){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.getMaterias());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> createdMateria(@RequestBody MateriaPostDTO materiaPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.createdMateria(materiaPostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMateria(@RequestBody MateriaPostDTO materiaPostDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(materiaService.putMateria(materiaPostDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMateria(@PathVariable Long id){
        materiaService.deleteMateria(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino con exito la materia");
    }
}
