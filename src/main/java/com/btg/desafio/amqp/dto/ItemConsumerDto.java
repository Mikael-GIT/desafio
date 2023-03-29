package com.btg.desafio.amqp.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemConsumerDto {

    @JsonProperty("produto")
    private String product;

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("preco")
    private BigDecimal price;
}
