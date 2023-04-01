package com.btg.desafio.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemResponseDto {

    private String product;

    private Integer quantity;

    private BigDecimal price;
}