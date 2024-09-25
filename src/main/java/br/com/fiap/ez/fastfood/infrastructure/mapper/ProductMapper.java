package br.com.fiap.ez.fastfood.infrastructure.mapper;

import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public class ProductMapper {

	// Convert from DTO to Domain
	public static Product toDomain(ProductDTO dto) {
		return new Product(null, dto.getName(), dto.getDescription(), dto.getPrice(), null); 
	}

	// Convert from Domain to ResponseDTO (with ID)
	public static ProductResponseDTO domainToResponseDto(Product product) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		if (product.getCategory() != null) {
			dto.setCategoryName(product.getCategory().getName());
		}

		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		return dto;
	}

	// Convert from Persistence Entity to Domain Model
	public static Product entityToDomain(ProductEntity entity) {
		Product product = new Product();

		product.setId(entity.getId());
		product.setName(entity.getName());
		product.setDescription(entity.getDescription());
		product.setPrice(entity.getPrice());
		product.setCategory(CategoryMapper.entityToDomain(entity.getCategory()));

		return product;

	}

	// Convert from Domain Model to Persistence Entity
	public static ProductEntity domainToEntity(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.setId(product.getId());
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.setCategory(CategoryMapper.domainToEntity(product.getCategory()));

		return entity;
	}

	// Convert from Optional<ProductEntity> to Product (Domain)
	public static Product optionalToDomain(Optional<ProductEntity> entityOptional) {
		return entityOptional.map(ProductMapper::entityToDomain).orElse(null);
	}

	// Convert from Optional<Product> to ProductEntity (for persistence)
	public static ProductEntity optionalToEntity(Optional<Product> productOptional) {
		return productOptional.map(ProductMapper::domainToEntity).orElse(null);
	}
}