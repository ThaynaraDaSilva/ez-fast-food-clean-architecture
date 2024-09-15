package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.domain.model.OrderItem;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

	public static OrderItem entityToDomain(OrderItemEntity entity) {
        return new OrderItem(
            null, // Skip mapping Order to avoid recursion
            ProductMapper.entityToDomain(entity.getProduct()), // Ensure this handles null
            entity.getQuantity(),
            entity.getPrice()
        );
    }

    public static OrderItemEntity domainToEntity(OrderItem orderItem) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProduct(ProductMapper.domainToEntity(orderItem.getProduct())); // Ensure this handles null
        entity.setQuantity(orderItem.getQuantity());
        entity.setPrice(orderItem.getPrice());
        return entity;
    }

    // New methods for handling lists
    public static List<OrderItem> entityToDomain(List<OrderItemEntity> entities) {
        return entities.stream()
                .map(OrderItemMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    public static List<OrderItemEntity> domainToEntity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemMapper::domainToEntity)
                .collect(Collectors.toList());
    }
}
