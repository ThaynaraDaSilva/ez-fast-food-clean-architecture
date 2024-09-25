package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductResponseDTO {
	
	@JsonProperty("id")
	private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
    
    @JsonProperty("category_name")
    private String categoryName;

    @Min(value = 0, message = "Preço deve ser positivo")
    @JsonProperty("price")
    private Double price;
    
    

	public ProductResponseDTO(Long id, @NotBlank(message = "Nome do produto é obrigatório") String name, String description,String categoryName,
			@Min(value = 0, message = "Preço deve ser positivo") Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryName = categoryName;
		this.price = price;
	}

	public ProductResponseDTO(Long id, @NotBlank(message = "Nome do produto é obrigatório") String name, String description,
			@Min(value = 0, message = "Preço deve ser positivo") Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public ProductResponseDTO() {
    }
	
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
