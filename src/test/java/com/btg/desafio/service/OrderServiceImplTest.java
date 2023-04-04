package com.btg.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.btg.desafio.exception.EntityNotFoundException;
import com.btg.desafio.repository.OrderRepository;
import com.btg.desafio.repository.entity.Item;
import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.dto.ItemConsumerDto;
import com.btg.desafio.service.dto.OrderConsumerDto;
import com.btg.desafio.service.impl.OrderServiceImpl;
import com.btg.desafio.service.mapper.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderConsumerDto orderConsumerDto;
    
    private Order order;

    private Item item;
    
    @BeforeEach
    public void setUp() {
        orderConsumerDto = new OrderConsumerDto();
        orderConsumerDto.setClientId(1);
        orderConsumerDto.setOrderId(2);
        orderConsumerDto.setItens(new ArrayList<>());
        orderConsumerDto.getItens().add(new ItemConsumerDto());

        order = new Order();
        order.setId("1");
        order.setClientId(1);
        order.setOrderId(2);
        order.setItens(new ArrayList<>());
        Item item = new Item();
        item.setPrice(new BigDecimal("25"));
        item.setQuantity(2);
        order.getItens().add(item);
    }

    @Test
    public void testSave() {
        when(orderMapper.toOrder(orderConsumerDto)).thenReturn(order);

        orderService.save(orderConsumerDto);

        verify(orderRepository).save(order);
    }

    @Test
    public void testGetOrdersByClientId() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findByClientId(any(Integer.class))).thenReturn(orders);

        List<Order> result = orderService.getOrdersByClientId(1);

        assertEquals(orders, result);
    }

    @Test
    public void testGetOrdersByClientIdNotFoundException() {
        when(orderRepository.findByClientId(any(Integer.class))).thenReturn(new ArrayList<>());

        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrdersByClientId(1);
        });
    }

    @Test
    public void testGetTotalValueOrderById() throws Exception {
        when(orderRepository.findByOrderId(any(Integer.class))).thenReturn(Optional.of(this.order));
        
        BigDecimal result = orderService.getTotalValueOrderById(1);

        assertEquals(BigDecimal.valueOf(50.0).setScale(2, RoundingMode.HALF_DOWN), result);
    }

    @Test
    public void testGetTotalValueOrderByIdNotFoundException() {
        when(orderRepository.findByOrderId(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getTotalValueOrderById(1);
        });
    }

    @Test
    public void testGetNumberOfOrdersByClientId() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findByClientId(any(Integer.class))).thenReturn(orders);

        int result = orderService.getNumberOfOrdersByClientId(1);

        assertEquals(1, result);
    }
    
}