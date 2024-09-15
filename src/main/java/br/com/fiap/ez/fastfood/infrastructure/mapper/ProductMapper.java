package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public class ProductMapper {

    // Converter de DTO para Domínio
    public static Product toDomain(ProductDTO dto) {
        return new Product(null, dto.getName(), dto.getDescription(), dto.getPrice());
    }

    // Converter de Domínio para ResponseDTO (com ID)
    public static ProductResponseDTO domainToResponseDto(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }

    // Converter de Entidade de Persistência para Domínio
    public static Product entityToDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }

    // Converter de Domínio para Entidade de Persistência
    public static ProductEntity domainToEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        return entity;
    }
    
    public static Product dtoToDomain(ProductResponseDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }
    
    public static ProductEntity dtoToEntity(ProductResponseDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}