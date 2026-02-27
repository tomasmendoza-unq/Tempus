package edu.ar.tempus.config.data.impl;

import edu.ar.tempus.config.data.DataSedeer;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.DiasSemana;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.MateriaService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

@Component
@Profile("dev")
public class DataSedeerImpl implements DataSedeer {

    private final MateriaService materiaService;
    private final ComisionService comisionService;

    public DataSedeerImpl(MateriaService materiaService, ComisionService comisionService) {
        this.materiaService = materiaService;
        this.comisionService = comisionService;
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

        // --- 2. COMISIONES PARA INGLES ---
        // Mañana: Lun 08-10
        comisionService.guardar(Comision.builder()
                        .clases(List.of(crearClase(DiasSemana.LUNES, 8, 10))).build(),
                ingles.getMateriaId());

        // Tarde: Lun 14-16
        comisionService.guardar(Comision.builder()
                        .clases(List.of(crearClase(DiasSemana.LUNES, 14, 16))).build(),
                ingles.getMateriaId());

        // --- 3. COMISIONES PARA MATEMATICA ---
        // Mañana (Solapa con Ingles Mañana): Lun 09-11
        comisionService.guardar(Comision.builder()
                        .clases(List.of(crearClase(DiasSemana.LUNES, 9, 11))).build(),
                matematica.getMateriaId());

        // Noche (Compatible con todo): Mie 18-20
        comisionService.guardar(Comision.builder()
                        .clases(List.of(crearClase(DiasSemana.MIERCOLES, 18, 20))).build(),
                matematica.getMateriaId());

        // --- 4. COMISIONES PARA PROGRAMACION ---
        // Doble turno: Mar 08-10 y Jue 08-10
        comisionService.guardar(Comision.builder()
                        .clases(List.of(
                                crearClase(DiasSemana.MARTES, 8, 10),
                                crearClase(DiasSemana.JUEVES, 8, 10)
                        )).build(),
                programacion.getMateriaId());

        System.out.println(">>> DataSeeder: Materias y Comisiones creadas con éxito.");
    }

    private ClaseHorario crearClase(DiasSemana dia, int inicio, int fin) {
        return ClaseHorario.builder()
                .dia(dia)
                .inicio(LocalTime.of(inicio, 0))
                .fin(LocalTime.of(fin, 0))
                .build();
    }
}