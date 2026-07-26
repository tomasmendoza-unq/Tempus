package edu.ar.tempus.feature.carrera.controller.dto;


import jakarta.validation.constraints.NotNull;

public record SuscripcionCarreraRequestDTO(
        @NotNull(message = "El id de la carrera es requerido")
        Long idCarrera
) {}