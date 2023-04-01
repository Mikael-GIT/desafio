package com.btg.desafio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;
import com.btg.desafio.document.Item;
import com.btg.desafio.document.Order;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.service.OrderService;
import com.btg.desafio.service.impl.OrderServiceImpl;
import com.btg.desafio.service.mapper.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    
    @Mock
    private OrderServiceImpl orderService;
    
    @Mock
    private OrderMapper orderMapper; 

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void initMocks() {
    }

    @Test
    public void testGetOrdersByClientWithNoOrders() throws Exception {

        int clientId = 1;
        TotalOrdersByClientResponseDto expectedResponse = new TotalOrdersByClientResponseDto(0, new ArrayList<>());
        when(orderService.getOrdersByClientId(clientId)).thenReturn(expectedResponse);

        ResponseEntity<TotalOrdersByClientResponseDto> response = orderController.getOrdersByClient(clientId);

        Assertions.assertEquals(expectedResponse, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetOrdersByClientWithOneOrder() throws Exception {

        int clientId = 1;
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId("1");
        order1.setClientId(clientId);
        orders.add(order1);
        TotalOrdersByClientResponseDto expectedResponse = new TotalOrdersByClientResponseDto(1, orders);
        when(orderService.getOrdersByClientId(clientId)).thenReturn(expectedResponse);

        ResponseEntity<TotalOrdersByClientResponseDto> response = orderController.getOrdersByClient(clientId);

        Assertions.assertEquals(expectedResponse, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetOrdersByClientWithMultipleOrders() throws Exception {

        int clientId = 1;
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId("1");
        order1.setClientId(clientId);
        Order order2 = new Order();
        order2.setId("2");
        order2.setClientId(clientId);
        Order order3 = new Order();
        order3.setId("3");
        order3.setClientId(clientId);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        
        TotalOrdersByClientResponseDto expectedResponse = new TotalOrdersByClientResponseDto(3, orders);
        when(orderService.getOrdersByClientId(clientId)).thenReturn(expectedResponse);

        ResponseEntity<TotalOrdersByClientResponseDto> response = orderController.getOrdersByClient(clientId);

        Assertions.assertEquals(expectedResponse, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetTotalOrderValueWithNoOrders() throws Exception {
        int clientId = 1;
        List<Order> orders = new ArrayList();
        when(orderRepository.findByClientId(clientId)).thenReturn(orders);

        TotalOrderValueResponseDto totalOrderValue = orderService.getTotalOrderValue(clientId);

        Assertions.assertEquals(BigDecimal.ZERO, totalOrderValue);
    }

    @Test
    public void testGetTotalOrderValueWithOneOrder() throws Exception {
        Order order1 = new Order();
        order1.setId("1");
        order1.setClientId(1);
        Item item = new Item();
        item.setPrice(new BigDecimal("10.0"));
        item.setQuantity(5);
        order1.setItens(new ArrayList<>());
        order1.getItens().add(item);
        Optional<Order> optionalOrder1 = Optional.of(order1);
        when(orderRepository.findByOrderId(anyInt())).thenReturn(optionalOrder1);
    
        TotalOrderValueResponseDto totalOrderValue = orderService.getTotalOrderValue(1);
    
        Assertions.assertEquals(new BigDecimal("50.00"), totalOrderValue.getTotalOrderValue());
    }

    @Test
    public void testGetTotalOrderValueWithMultipleOrders() throws Exception {
        int clientId = 1;
        Order order1 = new Order();
        order1.setId("1");
        order1.setClientId(clientId);
        Optional<Order> optionalOrder1 = Optional.of(order1);
        when(orderRepository.findByOrderId(clientId)).thenReturn(optionalOrder1);

        TotalOrderValueResponseDto totalOrderValue = orderService.getTotalOrderValue(clientId);

        Assertions.assertEquals(new BigDecimal("175.00"), totalOrderValue);
    }

}