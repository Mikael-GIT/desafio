package com.btg.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void initMocks() {
    }

    @Test
    public void testGetOrdersByClientId() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderService.getOrdersByClientId(any(Integer.class))).thenReturn(orders);

        List<Order> result = orderController.getOrdersByClientId(1);

        assertEquals(orders, result);
    }

    @Test
    public void testGetNumberOfOrdersByClientId() throws Exception {
        when(orderService.getNumberOfOrdersByClientId(any(Integer.class))).thenReturn(1);

        Integer result = orderController.getNumberOfOrdersByClientId(1);

        assertEquals(1, result);
    }

    @Test
    public void testGetTotalValueOrderById() throws Exception {
        when(orderService.getTotalValueOrderById(any(Integer.class))).thenReturn(BigDecimal.valueOf(10.00));

        BigDecimal result = orderController.getTotalValueOrderById(1);

        assertEquals(BigDecimal.valueOf(10.00), result);
    }
}