package com.java.products.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.products.dto.CreateOrderDTO;
import com.java.products.model.Order;
import com.java.products.repository.OrderRepository;
import com.java.products.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({ "/orders" })
public class OrderController {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderServiceImp;

	/**
	 * Return all orders for a given time period
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping
	public List findAllByDate(
			@RequestParam(value = "endDate", defaultValue = "2021-01-01 00:00:00", required = false) @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime endDate,
			@RequestParam(value = "startDate", defaultValue = "2019-01-01 00:00:00", required = false) @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime startDate) {

		return orderRepository.findByDateBetween(startDate, endDate);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Order> findById(@PathVariable long id) {
		return orderRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Place a new Order
	 *
	 * @param createOrderDTO
	 * @return
	 */
	@PostMapping
	public Order create(@RequestBody CreateOrderDTO createOrderDTO) {
		return orderServiceImp.create(createOrderDTO);
	}

}
