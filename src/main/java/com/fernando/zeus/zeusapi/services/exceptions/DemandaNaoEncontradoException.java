package com.fernando.zeus.zeusapi.services.exceptions;

public class DemandaNaoEncontradoException extends RuntimeException {

    public DemandaNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public DemandaNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
