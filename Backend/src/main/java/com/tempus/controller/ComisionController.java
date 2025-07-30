package com.tempus.controller;

import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.service.IComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comisiones")
public class ComisionController {

    @Autowired
    IComisionService comisionService;

    @PostMapping("/crear")
    public ResponseEntity<?> createdComision(@RequestBody ComisionPostDTO comisionPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createdComision(comisionPostDTO));
    }
}
