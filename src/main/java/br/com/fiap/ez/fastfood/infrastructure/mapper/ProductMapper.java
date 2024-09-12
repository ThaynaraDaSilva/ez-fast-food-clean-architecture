package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CategoryMapper; // Ensure this import is correct

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    // Convert ProductEntity (Persistence) to Product (Domain)
    public static Product entityToDomain(ProductEntity entity) {
        if (entity == null) {
            return null; // To avoid NullPointerException if entity is null
        }
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            CategoryMapper.entityToDomain(entity.getCategoryEntity()) // entity.getCategory() is of type CategoryEntity
        );
    }

    // Convert Product (Domain) to ProductEntity (Persistence)
    public static ProductEntity domainToEntity(Product product) {
        if (product == null) {
            return null; // To avoid NullPointerException if product is null
        }
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setCategoryEntity(CategoryMapper.domainToEntity(product.getCategory())); // product.getCategory() is of type Category
        return entity;
    }

    // Convert a list of ProductEntity (Persistence) to a list of Product (Domain)
    public static List<Product> entityToDomain(List<ProductEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of(); // Handle empty or null lists safely
        }
        return entities.stream()
                .map(ProductMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    // Convert a list of Product (Domain) to a list of ProductEntity (Persistence)
    public static List<ProductEntity> domainToEntity(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return List.of(); // Handle empty or null lists safely
        }
        return products.stream()
                .map(ProductMapper::domainToEntity)
                .collect(Collectors.toList());
    }
}
