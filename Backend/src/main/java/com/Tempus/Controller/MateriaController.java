package com.Tempus.Controller;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Services.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    IMateriaService materiaService;

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(materiaService.findByIdMateriaDTO(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> createdMateria(@RequestBody MateriaDTO materiaDTO){
        MateriaDTO response = materiaService.createdMateria(materiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
