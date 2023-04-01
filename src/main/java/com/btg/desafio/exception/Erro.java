package com.btg.desafio.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Erro {
    
    private String mensagem;

    private Integer statusCode;

    private LocalDateTime timeStamp;

}