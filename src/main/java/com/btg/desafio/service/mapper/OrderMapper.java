package com.btg.desafio.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.btg.desafio.repository.entity.Item;
import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.dto.OrderConsumerDto;

@Component
public class OrderMapper {

    public Order toOrder(OrderConsumerDto orderConsumerDto){
        List<Item> itens = new ArrayList<>();
        Order order = new Order();
        order.setClientId(orderConsumerDto.getClientId());
        order.setOrderId(orderConsumerDto.getOrderId());
        orderConsumerDto.getItens().forEach(item -> {
            Item newerItem = new Item();
            newerItem.setProduct(item.getProduct());
            newerItem.setPrice(item.getPrice());
            newerItem.setQuantity(item.getQuantity());
            itens.add(newerItem);
        });
        order.setItens(itens);
        return order;
    }
}