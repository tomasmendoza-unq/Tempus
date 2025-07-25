package com.Tempus.Controller;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.Services.IComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comision")
public class ComisionController {

    @Autowired
    IComisionService comisionService;

    @PostMapping("/crear")
    public ResponseEntity<?> createdComision(@RequestBody ComisionCreatedDTO comisionDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createdComision(comisionDTO));
    }

    
}
