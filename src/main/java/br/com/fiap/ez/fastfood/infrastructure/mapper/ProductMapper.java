package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

public class ProductMapper {

    // Converter de DTO para o domínio
    public static Product toDomain(ProductDTO dto) {
        return new Product(null, dto.getName(), dto.getDescription(), dto.getPrice());
    }

    // Converter de domínio para DTO
    public static ProductDTO domainToDto(Product product) {
        ProductDTO dto = new ProductDTO();
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
        return new ProductEntity(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    // Atualizar uma entidade existente com dados de um domínio
    public static void updateEntity(Product product, ProductEntity entity) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
    }
}
