package com.btg.desafio.service;

import com.btg.desafio.document.Order;

public interface ClientService {
    
    Order getOrders(Integer clientId);
}