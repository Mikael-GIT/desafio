package com.btg.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;
import com.btg.desafio.document.Order;
import com.btg.desafio.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<TotalOrdersByClientResponseDto> getOrdersByClient(@PathVariable Integer clientId) throws Exception{
        return ResponseEntity.ok().body(orderService.getOrdersByClientId(clientId));
    }

    @GetMapping("{orderId}")
    public ResponseEntity<TotalOrderValueResponseDto> getTotalOrderValue(@PathVariable Integer orderId) throws Exception{
        return ResponseEntity.ok().body(orderService.getTotalOrderValue(orderId));
    }

}