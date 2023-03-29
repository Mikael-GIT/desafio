package com.btg.desafio.repository.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "order")
@Getter
@Setter
public class Order {
    
    @Id
    private String id;

    private Integer orderId;

    private Integer clientId;

    private List<Item> itens;

}