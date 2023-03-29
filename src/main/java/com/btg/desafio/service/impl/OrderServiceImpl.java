package com.btg.desafio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.repository.domain.Item;
import com.btg.desafio.repository.domain.Order;
import com.btg.desafio.service.OrderService;
import com.btg.desafio.service.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public void save(OrderConsumerDto orderConsumerDto){
        
        Order order = new Order();
        order.setClientId(orderConsumerDto.getClientId());
        order.setOrderId(orderConsumerDto.getOrderId());
        orderConsumerDto.getItens().forEach(item -> {
            Item newerItem = new Item();
            newerItem.setProduct(item.getProduct());
            newerItem.setPrice(item.getPrice());
            newerItem.setQuantity(item.getQuantity());
        });
        orderRepository.save(order);
        //orderRepository.save(order);
    }
}