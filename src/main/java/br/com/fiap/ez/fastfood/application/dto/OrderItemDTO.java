package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.ez.fastfood.domain.model.Product;

public class OrderItemDTO {

	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("quantity")
	private Integer quantity;

	/*
	 * @JsonProperty("price") private Double price;
	 */


	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
