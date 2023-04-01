package com.btg.desafio.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class TotalOrderValueResponseDto {
    
    private Integer orderId;

    private List<ItemResponseDto> itens;

    private BigDecimal totalOrderValue;
}