package edu.ar.tempus.model;

import java.time.LocalTime;

public enum FranjasHorarias {
    MAÑANA(LocalTime.of(6, 0), LocalTime.of(12, 0)),
    TARDE(LocalTime.of(12, 0), LocalTime.of(18, 0)),
    NOCHE(LocalTime.of(18, 0), LocalTime.of(23, 59));

    private final LocalTime inicio;
    private final LocalTime fin;

    FranjasHorarias(LocalTime inicio, LocalTime fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public static FranjasHorarias fromHorario(LocalTime horario) {
        for (FranjasHorarias franja : values()) {
            if (!horario.isBefore(franja.inicio) && horario.isBefore(franja.fin)) {
                return franja;
            }
        }
        throw new IllegalArgumentException("Horario fuera de rango: " + horario);
    }
}