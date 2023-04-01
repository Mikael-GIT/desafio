package com.btg.desafio.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Erro> entityNotFoundException(EntityNotFoundException exception){
        Erro erro = Erro.builder()
            .mensagem(exception.getMessage())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .timeStamp(LocalDateTime.now()).build();
		return new ResponseEntity<Erro>(erro, HttpStatus.NOT_FOUND);
	}
}