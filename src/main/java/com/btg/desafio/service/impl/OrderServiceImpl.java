package com.btg.desafio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;
import com.btg.desafio.document.Order;
import com.btg.desafio.exception.EntityNotFoundException;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.service.OrderService;
import com.btg.desafio.service.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public void save(OrderConsumerDto orderConsumerDto){
        Order order = orderMapper.toOrder(orderConsumerDto);
        orderRepository.save(order);
    }


    @Override
    public TotalOrdersByClientResponseDto getOrdersByClientId(Integer clientId) throws Exception {
        List<Order> orders = orderRepository.findByClientId(clientId);
        if(!orders.isEmpty()) {
            return orderMapper.toOrdersByClientResponseDto(orders);
        }
        throw new EntityNotFoundException("Não foi possível encontrar pedidos para esse cliente.");
    }
    
    @Override
    public TotalOrderValueResponseDto getTotalOrderValue(Integer orderId) throws Exception {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new EntityNotFoundException("Não foi possível consultar o pedido."));
        TotalOrderValueResponseDto totalOrderValueResponseDto = orderMapper.toTotalValueResponseDto(order);
        return totalOrderValueResponseDto;
    }
}