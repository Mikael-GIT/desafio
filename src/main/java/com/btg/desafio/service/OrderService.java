package com.btg.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.dto.OrderConsumerDto;

public interface OrderService {
    
    void save(OrderConsumerDto orderConsumerDto);

    List<Order> getOrdersByClientId(Integer id) throws Exception;

    BigDecimal getTotalValueOrderById(Integer orderId) throws Exception;
    
    Integer getNumberOfOrdersByClientId(Integer clientId) throws Exception;
}