package com.java.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateOrderItemDTO {

    private  Long productId;
    private Integer quantity;
	public CreateOrderItemDTO(Long id, int i) {
		this.productId = id;
		this.quantity =i;
		
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public Integer getQuantity() {
		return quantity;
	}

}
