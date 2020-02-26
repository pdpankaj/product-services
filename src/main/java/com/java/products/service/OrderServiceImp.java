package com.java.products.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.products.dto.CreateOrderDTO;
import com.java.products.dto.CreateOrderItemDTO;
import com.java.products.exception.InvalidOrderException;
import com.java.products.exception.ProductNotFoundException;
import com.java.products.model.Order;
import com.java.products.model.OrderItem;
import com.java.products.model.Product;
import com.java.products.repository.OrderRepository;
import com.java.products.repository.ProductRepository;

import static com.java.products.model.Order.MAX_ALLOWED_QUANTITY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class OrderServiceImp implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;

    public Order create(CreateOrderDTO createOrderDTO){
        List<CreateOrderItemDTO> dtoOrderItems = createOrderDTO.getOrderItems();

        List<OrderItem> items = new ArrayList<>();
        for (CreateOrderItemDTO i : dtoOrderItems){
            Product productInRepository = checkInput(i);
            OrderItem orderItem = new OrderItem(productInRepository, i.getQuantity());
            items.add(orderItem);
        }

        return orderRepository.save(new Order(createOrderDTO.getUserEmail(), items));
    }

    /**
     * Applies some checks on the order item input
     * @param itemDTO
     * @return
     */
    private Product checkInput(CreateOrderItemDTO itemDTO) {
        // check if the product exists in db
        Long id = itemDTO.getProductId();
        Optional<Product> productInRepository = productRepository.findById(id);
        if(!productInRepository.isPresent()){
            throw new ProductNotFoundException("Please create the product first #id:" + id);
        }

        // check quantity
        if(itemDTO.getQuantity() < 1 || itemDTO.getQuantity() > MAX_ALLOWED_QUANTITY){
            String err = String.format("Invalid quantity (%d) on product #id%d", itemDTO.getQuantity(), id);
            throw new InvalidOrderException(err);
        }

        return productInRepository.get();
    }
}
