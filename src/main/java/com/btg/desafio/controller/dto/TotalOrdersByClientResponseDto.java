package com.btg.desafio.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class TotalOrdersByClientResponseDto {
    
    private Integer orderId;

    private List<ItemResponseDto> itens;

    private Long totalNumberOfOrders;
}