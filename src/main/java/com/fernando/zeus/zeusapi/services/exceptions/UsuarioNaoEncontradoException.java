package com.fernando.zeus.zeusapi.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
