package com.btg.desafio.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/clients/{clientId}/orders")
    public List<Order> getOrdersByClientId(@PathVariable @Min(1) Integer clientId) throws Exception{
        return orderService.getOrdersByClientId(clientId);
    }

    @GetMapping("/clients/{clientId}")
    public Integer getNumberOfOrdersByClientId(@PathVariable @Min(1) Integer clientId) throws Exception{
        return orderService.getNumberOfOrdersByClientId(clientId);
    }

    @GetMapping("/{id}")
    public BigDecimal getTotalValueOrderById(@PathVariable @Min(1) Integer id) throws Exception{
        return orderService.getTotalValueOrderById(id);
    }

}