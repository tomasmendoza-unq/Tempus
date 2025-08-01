package com.tempus.enums;

import java.time.LocalTime;

public enum Turno {
    MAÑANA(LocalTime.of(6, 0)),
    TARDE(LocalTime.NOON),
    NOCHE(LocalTime.of(18, 0));

    private final LocalTime inicio;

    Turno(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getInicio() {
        return inicio;
    }
}
