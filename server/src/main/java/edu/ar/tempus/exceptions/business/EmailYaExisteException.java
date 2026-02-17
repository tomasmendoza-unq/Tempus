package edu.ar.tempus.exceptions.business;

public class EmailYaExisteException extends BusinessException {
    public EmailYaExisteException(String mensaje) {
        super(mensaje);
    }
}