package com.btg.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.document.Order;

public interface OrderService {
    
    void save(OrderConsumerDto orderConsumerDto);

    List<Order> getOrdersByClientId(Integer id) throws Exception;

    BigDecimal getTotalValueOrderById(Integer orderId) throws Exception;
    
    Integer getNumberOfOrdersByClientId(Integer clientId) throws Exception;
}