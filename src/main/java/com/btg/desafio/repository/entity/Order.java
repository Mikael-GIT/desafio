package com.btg.desafio.repository.entity;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "order")
@Data
public class Order {
    
    @Id
    private String id;

    private Integer orderId;

    private Integer clientId;

    private List<Item> itens;


    public Double totalOrderValue() throws Exception{
        if(Objects.nonNull(itens)){
            return itens.stream().mapToDouble(item -> item.getPrice().doubleValue() * item.getQuantity()).sum();
        }
        throw new Exception("Não foi possível obter valor total do pedido.");
    }

}