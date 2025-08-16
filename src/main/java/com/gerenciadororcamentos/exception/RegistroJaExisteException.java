package com.gerenciadororcamentos.exception;

public class RegistroJaExisteException extends RuntimeException {

    public RegistroJaExisteException(String message) {
        super(message);
    }
}
