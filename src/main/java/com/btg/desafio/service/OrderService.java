package com.btg.desafio.service;

import com.btg.desafio.amqp.dto.OrderConsumerDto;

public interface OrderService {
    
    void save(OrderConsumerDto orderConsumerDto);
}