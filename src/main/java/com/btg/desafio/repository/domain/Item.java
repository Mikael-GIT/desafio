package com.btg.desafio.repository.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Item {

    private String product;

    private Integer quantity;

    private BigDecimal price;
}