package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    @JsonProperty("nome")
    private String name;

    @JsonProperty("descrição")
    private String description;

    @Min(value = 0, message = "Preço deve ser positivo")
    @JsonProperty("preço")
    private Double price;

	public ProductDTO(@NotBlank(message = "Nome do produto é obrigatório") String name, String description,
			@Min(value = 0, message = "Preço deve ser positivo") Double price) {
		super();
		this.name = name;
		this.description = description;
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

}
