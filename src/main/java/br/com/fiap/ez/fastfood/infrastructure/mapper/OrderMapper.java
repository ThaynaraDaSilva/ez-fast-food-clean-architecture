package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderItem;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

	// Convert OrderEntity (Persistence) to Order (Domain)
	public static Order entityToDomain(OrderEntity entity) {
		System.out.println("ENTREI NO ENTITY TO DOMAIN");
		Order order = new Order();
		order.setId(entity.getId());
		if (entity.getCustomer() != null) {
			order.setCustomer(CustomerMapper.entityToDomain(entity.getCustomer()));
		}
		order.setCustomerName(entity.getCustomerName());
		order.setOrderTime(entity.getOrderTime());
		order.setCompletedTime(entity.getCompletedTime());
		order.setTotalPrice(entity.getTotalPrice());
		order.setStatus(entity.getStatus());
		order.setOrderItems(OrderItemMapper.entityToDomain(entity.getOrderItems()));

		System.out.println("SAI DO ENTITY TO DOMAIN");
		return order;
	}

	// Convert Order (Domain) to OrderEntity (Persistence)
	public static OrderEntity domainToEntity(Order order) {
		if (order == null) {
			return null;
		}
		OrderEntity entity = new OrderEntity();
		entity.setId(order.getId());
		if (order.getCustomer() != null) {
			entity.setCustomer(CustomerMapper.domainToEntity(order.getCustomer()));
		} else {
			entity.setCustomer(null); // Set customer as null if it's not present
		}
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
		return entities.stream().map(OrderMapper::entityToDomain).collect(Collectors.toList());
	}

	// Convert a List of Order (Domain) to a List of OrderEntity (Persistence)
	public static List<OrderEntity> domainToEntity(List<Order> orders) {
		return orders.stream().map(OrderMapper::domainToEntity).collect(Collectors.toList());
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
		
		 orderResponseDTO.setWaitedTime(order.calculateOrderWaitedTime(order.getOrderTime()));

		// Map Order Items to DTO
		List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
				.map(orderItem -> new OrderItemDTO(orderItem.getProduct().getId(), orderItem.getQuantity()))
				.collect(Collectors.toList());
		orderResponseDTO.setOrderItems(orderItemDTOs);

		return orderResponseDTO;
	}

	public static OrderResponseDTO entityToOrderResponseDTO(OrderEntity entity) {
    	System.out.println("ENTREI NO ENTITY TO DOMAIN");
    	Order order = new Order();
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(entity.getId());
        if(entity.getCustomer()!= null) {
        	orderResponseDTO.setCustomerCpf(entity.getCustomer().getCpf());
        }
        orderResponseDTO.setCustomerName(entity.getCustomerName());
        orderResponseDTO.setOrderTime(entity.getOrderTime());
        orderResponseDTO.setCompletedTime( entity.getCompletedTime());
        orderResponseDTO.setTotalPrice(entity.getTotalPrice());
        orderResponseDTO.setOrderStatus(entity.getStatus());
        orderResponseDTO.setWaitedTime(order.calculateOrderWaitedTime(entity.getOrderTime()));
        List<OrderItemDTO> orderItemDTOs = entity.getOrderItems().stream()
				.map(orderItem -> new OrderItemDTO(orderItem.getProduct().getId(), orderItem.getQuantity()))
				.collect(Collectors.toList());
        orderResponseDTO.setOrderItems(orderItemDTOs);
        System.out.println("SAI DO ENTITY TO DOMAIN");
        return orderResponseDTO;
    }
}
