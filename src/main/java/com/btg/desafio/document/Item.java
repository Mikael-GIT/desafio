package com.btg.desafio.document;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Item {

    private String product;

    private Integer quantity;

    private BigDecimal price;
}