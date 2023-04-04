package com.btg.desafio.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.btg.desafio.repository.entity.Order;
import com.btg.desafio.service.dto.ItemConsumerDto;
import com.btg.desafio.service.dto.OrderConsumerDto;
import com.btg.desafio.service.mapper.OrderMapper;


@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    private OrderMapper orderMapper;

    @BeforeEach
    public void setUp() {
        orderMapper = new OrderMapper();
    }

    @Test
    public void shouldMapToOrder() {
        OrderConsumerDto orderConsumerDto = new OrderConsumerDto();
        orderConsumerDto.setClientId(1);
        orderConsumerDto.setOrderId(1);
        ItemConsumerDto item = new ItemConsumerDto();
        item.setPrice(BigDecimal.TEN);
        item.setQuantity(5);
        item.setProduct("Product");
        orderConsumerDto.setItens(Arrays.asList(item));

        Order order = orderMapper.toOrder(orderConsumerDto);

        assertEquals(5, order.getItens().get(0).getQuantity());
    }
}