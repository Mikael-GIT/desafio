package com.btg.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.desafio.document.Order;
import com.btg.desafio.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/clients/{clientId}/orders")
    public List<Order> getOrdersByClientId(@PathVariable Integer clientId) throws Exception{
        return orderService.getOrdersByClientId(clientId);
    }

    @GetMapping("/clients/{clientId}")
    public Integer getNumberOfOrdersByClientId(@PathVariable Integer orderId) throws Exception{
        return orderService.getNumberOfOrdersByClientId(orderId);
    }

    @GetMapping("/{id}")
    public Integer getTotalValueOrderById(@PathVariable Integer id) throws Exception{
        return orderService.getNumberOfOrdersByClientId(id);
    }

}