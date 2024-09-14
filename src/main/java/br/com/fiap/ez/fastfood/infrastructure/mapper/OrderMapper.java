package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // Convert OrderEntity (Persistence) to Order (Domain)
    public static Order entityToDomain(OrderEntity entity) {
        if (entity == null) {
            return null; 
        }
        return new Order(
            entity.getId(),
            CustomerMapper.entityToDomain(entity.getCustomer()), 
            entity.getOrderTime(),
            entity.getCompletedTime(),
            entity.getTotalPrice(),
            entity.getStatus(),
            entity.getCustomerName(),
            OrderItemMapper.entityToDomain(entity.getOrderItems()) 
        );
    }

    // Convert Order (Domain) to OrderEntity (Persistence)
    public static OrderEntity domainToEntity(Order order) {
        if (order == null) {
            return null; 
        }
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setCustomer(CustomerMapper.domainToEntity(order.getCustomer())); 
        entity.setOrderTime(order.getOrderTime());
        entity.setCompletedTime(order.getCompletedTime());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setStatus(order.getStatus());
        entity.setCustomerName(order.getCustomerName());
        entity.setOrderItems(OrderItemMapper.domainToEntity(order.getOrderItems())); 
        return entity;
    }

    // Convert a List of OrderEntity (Persistence) to a List of Order (Domain)
    public static List<Order> entityToDomain(List<OrderEntity> entities) {
        return entities.stream()
                .map(OrderMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    // Convert a List of Order (Domain) to a List of OrderEntity (Persistence)
    public static List<OrderEntity> domainToEntity(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::domainToEntity)
                .collect(Collectors.toList());
    }
    
    public static OrderResponseDTO domainToResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getId());

        orderResponseDTO.setOrderTime(order.getOrderTime());

        orderResponseDTO.setTotalPrice(order.getTotalPrice());

        if (order.getCustomer() != null) {
            orderResponseDTO.setCustomerCpf(order.getCustomer().getCpf());
        }

        orderResponseDTO.setCustomerName(order.getCustomerName());
        orderResponseDTO.setOrderStatus(order.getStatus());

        // Map Order Items to DTO
        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDTO(orderItem.getProduct().getId(), orderItem.getQuantity()))
                .collect(Collectors.toList());
        orderResponseDTO.setOrderItems(orderItemDTOs);

        return orderResponseDTO;
    }
}
