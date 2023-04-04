package com.btg.desafio.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderConsumerDto {
    
    @JsonProperty("codigoPedido")
    private Integer orderId;

    @JsonProperty("codigoCliente")
    private Integer clientId;

    @JsonProperty("itens")
    private List<ItemConsumerDto> itens;
}