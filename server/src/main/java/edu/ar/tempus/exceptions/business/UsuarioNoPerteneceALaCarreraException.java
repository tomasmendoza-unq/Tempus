package edu.ar.tempus.exceptions.business;

public class UsuarioNoPerteneceALaCarreraException extends BusinessException{
    public UsuarioNoPerteneceALaCarreraException(String mensaje) {
        super(mensaje);
    }
}
