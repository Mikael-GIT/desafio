package com.btg.desafio.service;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;

public interface OrderService {
    
    void save(OrderConsumerDto orderConsumerDto);

    TotalOrdersByClientResponseDto getOrdersByClientId(Integer id) throws Exception;

    TotalOrderValueResponseDto getTotalOrderValue(Integer orderId) throws Exception;
}