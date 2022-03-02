package com.gerenciador_de_reserva.model;

public enum AmenityTipo {
    POOL("POOL"), SAUNA("SAUNA"), CONDOMINIO("CONDOMINIO"),
    ACADEMIA("ACADEMIA"), SALA("SALA");

    private final String name;

    private AmenityTipo(String value) {
        name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
