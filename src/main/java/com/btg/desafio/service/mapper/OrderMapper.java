package com.btg.desafio.service.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.controller.dto.ItemResponseDto;
import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;
import com.btg.desafio.document.Item;
import com.btg.desafio.document.Order;

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

    public TotalOrderValueResponseDto toTotalValueResponseDto(Order order) throws Exception {
        TotalOrderValueResponseDto orderResponse = new TotalOrderValueResponseDto();
        orderResponse.setOrderId(order.getOrderId());
        order.getItens().forEach(item -> {
            ItemResponseDto newerItem = new ItemResponseDto();
            newerItem.setProduct(item.getProduct());
            newerItem.setPrice(item.getPrice());
            newerItem.setQuantity(item.getQuantity());
            
            orderResponse.setItens(new ArrayList<>());
            orderResponse.getItens().add(newerItem);
        });
        orderResponse.setTotalOrderValue(BigDecimal.valueOf(order.totalOrderValue()));
        return orderResponse;
    };


    public TotalOrdersByClientResponseDto toOrdersByClientResponseDto(List<Order> orders) throws Exception {
        TotalOrdersByClientResponseDto totalOrderValueResponseDto = new TotalOrdersByClientResponseDto();
        orders.forEach(order -> {
            totalOrderValueResponseDto.setOrderId(order.getOrderId());
            order.getItens().forEach(item -> {
                ItemResponseDto newerItem = new ItemResponseDto();
                newerItem.setProduct(item.getProduct());
                newerItem.setPrice(item.getPrice());
                newerItem.setQuantity(item.getQuantity());
                totalOrderValueResponseDto.setItens(new ArrayList<>());
                totalOrderValueResponseDto.getItens().add(newerItem);
            });
        });
        totalOrderValueResponseDto.setTotalNumberOfOrders(orders.stream().count());
        orders.stream().count();
        return totalOrderValueResponseDto;
    };
    

}