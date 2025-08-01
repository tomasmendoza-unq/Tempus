package com.tempus.controller;

import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.service.IComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comisiones")
public class ComisionController {

    @Autowired
    IComisionService comisionService;

    @PostMapping("/crear")
    public ResponseEntity<?> createdComision(@RequestBody ComisionPostDTO comisionPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createdComision(comisionPostDTO));
    }

    @GetMapping
    public ResponseEntity<?> getComisiones(){
        return ResponseEntity.status(HttpStatus.OK).body(comisionService.getComisiones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComision(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(comisionService.getComision(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putComision(@RequestBody ComisionPostDTO comisionPostDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comisionService.putComision(comisionPostDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComision(@PathVariable Long id){
        comisionService.deleteComision(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino con exito");
    }
}
