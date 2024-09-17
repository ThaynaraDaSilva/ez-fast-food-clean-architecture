package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderItem;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderItemEntity;


public class OrderRepositoryImpl implements OrderRepository {
	
	private final JpaOrderRepository jpaOrderRepository;

	

	public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository) {
		this.jpaOrderRepository = jpaOrderRepository;
	}
	
	
	@Override
	public Order save(Order order) {
        OrderEntity entity = OrderMapper.domainToEntity(order);
		/*
		 * System.out.println("==========================================");
		 * for(OrderItem ent: order.getOrderItems()) {
		 * System.out.println("ORDER ITEMS ORDER ID: " + ent.getOrder().getId()); }
		 * System.out.println("==========================================");
		 */
        System.out.println("===================ANTES DO SAVE ENTITY=======================");
        jpaOrderRepository.save(entity);
        System.out.println("===================APOS DO SAVE ENTITY=======================");
        return order;
    }


	
	public List<Order> findAll() {
		return jpaOrderRepository.findAll().stream().map(OrderMapper::entityToDomain)
				.collect(Collectors.toList());
	}

	


	@Override
	public Order findOrderById(Long id) {
		OrderEntity entity = jpaOrderRepository.findById(id).orElse(null);
        return OrderMapper.entityToDomain(entity);
	}


    public List<Order> listUnfinishedOrders() {
        return jpaOrderRepository.listUnfinishedOrders().stream()
                .map(OrderMapper::entityToDomain)
                .collect(Collectors.toList());
    }

}
