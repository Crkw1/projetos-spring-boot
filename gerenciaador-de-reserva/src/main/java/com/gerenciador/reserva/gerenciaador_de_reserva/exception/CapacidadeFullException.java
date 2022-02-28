package com.gerenciador.reserva.gerenciaador_de_reserva.exception;

public class CapacidadeFullException extends RuntimeException {
    public CapacidadeFullException(String message) {
        super(message);
    }
}
