package com.Tempus.Controller;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.Services.IComisionService;
import org.apache.coyote.Response;
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
    public ResponseEntity<?> createdComision(@RequestBody ComisionCreatedDTO comisionDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createdComision(comisionDTO));
    }

    @GetMapping
    public ResponseEntity<?> getComisiones(){
        return ResponseEntity.status(HttpStatus.OK).body(comisionService.getComisiones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComision(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(comisionService.getComision(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putComision(@PathVariable Long id, @RequestBody ComisionCreatedDTO comisionCreatedDTO){
        return ResponseEntity.status(HttpStatus.OK).body(comisionService.putComision(id, comisionCreatedDTO));
    }
}
