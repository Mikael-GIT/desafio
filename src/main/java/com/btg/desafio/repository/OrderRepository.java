package com.btg.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.btg.desafio.repository.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer>{
    
    List<Order> findByClientId(Integer clientId);

    Optional<Order> findByOrderId(Integer orderId);
}