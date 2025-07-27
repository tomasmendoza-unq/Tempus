package com.tempus.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Carrera {


    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarrera;

    private String nombre;


}
