package com.btg.desafio.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.btg.desafio.amqp.dto.OrderConsumerDto;
import com.btg.desafio.service.OrderService;

@Component
public class OrderListener {
    
    @Autowired 
    private OrderService orderService;

    @RabbitListener(queues = "order.submited")
    public void recieveMessage(OrderConsumerDto orderConsumerDto){
        orderService.save(orderConsumerDto);
    }
}