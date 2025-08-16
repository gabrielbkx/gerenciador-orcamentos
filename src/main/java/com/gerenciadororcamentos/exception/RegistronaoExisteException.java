package com.gerenciadororcamentos.exception;

public class RegistronaoExisteException extends RuntimeException {
    public RegistronaoExisteException(String message) {
        super(message);
    }
}
