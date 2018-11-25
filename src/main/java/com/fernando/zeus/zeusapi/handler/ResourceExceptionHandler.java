package com.fernando.zeus.zeusapi.handler;

import com.fernando.zeus.zeusapi.domain.DetalhesErro;
import com.fernando.zeus.zeusapi.services.exceptions.DemandaNaoEncontradoException;
import com.fernando.zeus.zeusapi.services.exceptions.UsuarioExistenteException;
import com.fernando.zeus.zeusapi.services.exceptions.UsuarioNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DemandaNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleDemandaNaoEncontradaException(DemandaNaoEncontradoException d
            , HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("Demanda não encontrada");
        erro.setMensagemDesenvolvedor("http://erros.zeusapi.com/404");
        erro.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<DetalhesErro> handleUsuarioExistenteException(UsuarioExistenteException u
            , HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Usuário já existente");
        erro.setMensagemDesenvolvedor("http://erros.zeusapi.com/409");
        erro.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleUsuarioExistenteException(UsuarioNaoEncontradoException u
            , HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("Usuário não Encontrado");
        erro.setMensagemDesenvolvedor("http://erros.zeusapi.com/404");
        erro.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException u
            , HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição inválida");
        erro.setMensagemDesenvolvedor("http://erros.zeusapi.com/400");
        erro.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}
