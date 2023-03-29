package com.btg.desafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.btg.desafio.repository.domain.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer>{
    
}