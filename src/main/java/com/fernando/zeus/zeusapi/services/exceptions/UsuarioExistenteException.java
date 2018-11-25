package com.fernando.zeus.zeusapi.services.exceptions;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String mensagem){
        super(mensagem);
    }

    public UsuarioExistenteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
