package com.java.products.service;

import com.java.products.dto.CreateOrderDTO;
import com.java.products.model.Order;

public interface OrderService {
    Order create(CreateOrderDTO createOrderDTO);
}
