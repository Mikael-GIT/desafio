package com.btg.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.controller.dto.TotalOrderValueResponseDto;
import com.btg.desafio.controller.dto.TotalOrdersByClientResponseDto;
import com.btg.desafio.document.Order;
import com.btg.desafio.exception.EntityNotFoundException;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.service.impl.OrderServiceImpl;
import com.btg.desafio.service.mapper.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testSaveOrderSuccessfully() {
        OrderConsumerDto orderConsumerDto = new OrderConsumerDto();
        Order order = new Order();
        when(orderMapper.toOrder(orderConsumerDto)).thenReturn(order);

        orderService.save(orderConsumerDto);

        verify(orderRepository).save(order);
    }

    @Test
    public void testSaveOrderThrowsException() {
        OrderConsumerDto orderConsumerDto = new OrderConsumerDto();
        when(orderMapper.toOrder(orderConsumerDto)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            orderService.save(orderConsumerDto);
        });
    }


    @Test
    public void testGetOrdersByClientIdSuccessfully() throws Exception {

        Integer clientId = 1;
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId("1");
        Order order2 = new Order();
        order2.setId("2");
        orders.add(order1);
        orders.add(order2);
        TotalOrdersByClientResponseDto expectedResponse = new TotalOrdersByClientResponseDto();
        expectedResponse.setTotalNumberOfOrders((long) orders.size());
        when(orderRepository.findByClientId(clientId)).thenReturn(orders);
        when(orderMapper.toOrdersByClientResponseDto(orders)).thenReturn(expectedResponse);

        TotalOrdersByClientResponseDto response = orderService.getOrdersByClientId(clientId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetOrdersByClientIdThrowsExceptionWhenOrdersIsEmpty() {

        Integer clientId = 1;
        List<Order> orders = new ArrayList<>();
        when(orderRepository.findByClientId(clientId)).thenReturn(orders);


        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrdersByClientId(clientId);
        });
    }

    @Test
    public void testGetTotalOrderValueSuccessfully() throws Exception {

        Integer orderId = 1;
        Order order = new Order();
        order.setId("1");
        TotalOrderValueResponseDto expectedResponse = new TotalOrderValueResponseDto();
        expectedResponse.setOrderId(orderId);
        expectedResponse.setTotalOrderValue(new BigDecimal("50.00"));
        when(orderRepository.findByOrderId(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toTotalValueResponseDto(order)).thenReturn(expectedResponse);


        TotalOrderValueResponseDto response = orderService.getTotalOrderValue(orderId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetTotalOrderValueThrowsExceptionWhenOrderNotFound() {

        Integer orderId = 1;
        when(orderRepository.findByOrderId(orderId)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getTotalOrderValue(orderId);
        });
    }
}