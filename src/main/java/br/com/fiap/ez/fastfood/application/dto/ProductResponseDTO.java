package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponseDTO {
	
	@JsonProperty("product_id")
	private Long id;
	@JsonProperty("category_id")
	private Long categoryId;
	@JsonProperty("description")
    private String description;
	@JsonProperty("name")
    private String name;
	@JsonProperty("price")
    private Double price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
