package com.Tempus.Enums;

public enum TipoDeMateria {
    CORRELATIVA("Correlativa"),
    SIMPLE("Simple");

    private final String tipo;

    TipoDeMateria(String nombre) {
        this.tipo = nombre;
    }

    public String getTipo() {
        return tipo;
    }
}
