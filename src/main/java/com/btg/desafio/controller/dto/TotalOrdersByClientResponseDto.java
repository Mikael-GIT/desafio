package com.btg.desafio.controller.dto;

import java.util.List;

import com.btg.desafio.repository.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalOrdersByClientResponseDto {
    

    public TotalOrdersByClientResponseDto(int i, List<Order> orders) {
    }

    private Integer orderId;

    private List<ItemResponseDto> itens;

    private Long totalNumberOfOrders;
}