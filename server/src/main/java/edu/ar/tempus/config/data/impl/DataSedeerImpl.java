package edu.ar.tempus.config.data.impl;

import edu.ar.tempus.config.data.DataSedeer;
import edu.ar.tempus.model.*;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.MateriaService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("dev")
public class DataSedeerImpl implements DataSedeer {

    private final MateriaService materiaService;
    private final ComisionService comisionService;
    private final CarreraService carreraService;

    public DataSedeerImpl(MateriaService materiaService, ComisionService comisionService, CarreraService carreraService) {
        this.materiaService = materiaService;
        this.comisionService = comisionService;
        this.carreraService = carreraService;
    }

    @Override
    public void run(String... args) {
        // --- 1. MATERIAS ---
        Materia ingles = materiaService.guardar(Materia.builder()
                .materiaNombre("Ingles I").correlativas(new HashSet<>()).build());

        Materia matematica = materiaService.guardar(Materia.builder()
                .materiaNombre("Matematica I").correlativas(new HashSet<>()).build());

        Materia programacion = materiaService.guardar(Materia.builder()
                .materiaNombre("Programacion I").correlativas(new HashSet<>()).build());

        // --- 2. CARRERAS ---
        carreraService.guardar(
                Carrera.builder().nombreCarrera("Ingeniería en Sistemas").build(),
                Set.of(ingles.getMateriaId(), matematica.getMateriaId(), programacion.getMateriaId())
        );

        carreraService.guardar(
                Carrera.builder().nombreCarrera("Licenciatura en Administración").build(),
                Set.of(ingles.getMateriaId(), matematica.getMateriaId())
        );

        // --- 3. COMISIONES PARA INGLES ---
        comisionService.guardar(Comision.builder()
                        .comisionNombre("90000-1-G14 (Virtual)") 
                        .clases(List.of(crearClase(DiasSemana.LUNES, 8, 10))).build(),
                ingles.getMateriaId());

        comisionService.guardar(Comision.builder()
                        .comisionNombre("90000-2-G14 (Virtual)") 
                        .clases(List.of(crearClase(DiasSemana.LUNES, 14, 16))).build(),
                ingles.getMateriaId());

        // --- 4. COMISIONES PARA MATEMATICA ---
        comisionService.guardar(Comision.builder()
                        .comisionNombre("1033-1-G14 (Presencial)") 
                        .clases(List.of(crearClase(DiasSemana.LUNES, 9, 11))).build(),
                matematica.getMateriaId());

        comisionService.guardar(Comision.builder()
                        .comisionNombre("1033-2-G14 (Presencial)") 
                        .clases(List.of(crearClase(DiasSemana.MIERCOLES, 18, 20))).build(),
                matematica.getMateriaId());

        // --- 5. COMISIONES PARA PROGRAMACION ---
        comisionService.guardar(Comision.builder()
                        .comisionNombre("487-1-G14 (Presencial)") 
                        .clases(List.of(
                                crearClase(DiasSemana.MARTES, 8, 10),
                                crearClase(DiasSemana.JUEVES, 8, 10)
                        )).build(),
                programacion.getMateriaId());

        System.out.println(">>> DataSeeder: Materias, Carreras y Comisiones creadas con éxito.");
    }

    private ClaseHorario crearClase(DiasSemana dia, int inicio, int fin) {
        return ClaseHorario.builder()
                .dia(dia)
                .inicio(LocalTime.of(inicio, 0))
                .fin(LocalTime.of(fin, 0))
                .build();
    }
}