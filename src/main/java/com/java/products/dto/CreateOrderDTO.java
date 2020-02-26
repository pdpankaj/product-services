package com.java.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CreateOrderDTO {

	public CreateOrderDTO(String userEmail, List<CreateOrderItemDTO> list) {
		this.userEmail = userEmail;
		this.orderItems = list;

	}

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public List<CreateOrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<CreateOrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	private String userEmail;
	private List<CreateOrderItemDTO> orderItems;
}
