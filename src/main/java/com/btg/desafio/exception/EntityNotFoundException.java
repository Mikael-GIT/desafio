package com.btg.desafio.exception;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String mensagem) {
        super(mensagem);
    }

}