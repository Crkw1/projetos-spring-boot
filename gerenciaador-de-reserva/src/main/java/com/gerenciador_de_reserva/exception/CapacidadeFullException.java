package com.gerenciador_de_reserva.exception;

public class CapacidadeFullException extends RuntimeException {
    public CapacidadeFullException(String message) {
        super(message);
    }
}
