package com.btg.desafio.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.desafio.exception.EntityNotFoundException;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.OrderService;
import com.btg.desafio.service.dto.OrderConsumerDto;
import com.btg.desafio.service.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save(OrderConsumerDto orderConsumerDto){
        Order order = orderMapper.toOrder(orderConsumerDto);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByClientId(Integer clientId) throws Exception {
        List<Order> orders = orderRepository.findByClientId(clientId);
        if(!orders.isEmpty()) {
            return orders;
        }
        throw new EntityNotFoundException("Não foi possível encontrar pedidos para esse cliente.");
    }
    
    @Override
    public BigDecimal getTotalValueOrderById(Integer orderId) throws Exception {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new EntityNotFoundException("Não foi possível consultar o pedido."));
        return BigDecimal.valueOf(order.totalOrderValue()).setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public Integer getNumberOfOrdersByClientId(Integer clientId) throws Exception {
        return (int) getOrdersByClientId(clientId).stream().count();
    }


}