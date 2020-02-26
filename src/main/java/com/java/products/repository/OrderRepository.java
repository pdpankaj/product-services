package com.java.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.java.products.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findByDateBetween(LocalDateTime from, LocalDateTime to);
}
