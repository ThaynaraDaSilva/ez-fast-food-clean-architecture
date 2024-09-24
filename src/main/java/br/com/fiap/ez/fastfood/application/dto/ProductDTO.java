package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("description")
    private String description;

    @Min(value = 0, message = "Preço deve ser positivo")
    @JsonProperty("price")
    private Double price;

	public ProductDTO(@NotBlank(message = "Nome do produto é obrigatório") String name, String description,Long categoryId,
			@Min(value = 0, message = "Preço deve ser positivo") Double price) {
		super();
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.price = price;
	}

	public ProductDTO() {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


}
