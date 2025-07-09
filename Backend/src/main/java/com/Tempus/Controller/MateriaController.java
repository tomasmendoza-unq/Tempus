package com.Tempus.Controller;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
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

    @GetMapping("/correlativas/{id}")
    public ResponseEntity<MateriaDTO> getCorrelativas(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.getCorrelativas(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> setMateria(@RequestBody MateriaDTO materiaDTO){
        MateriaDTO response = materiaService.createdMateria(materiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
