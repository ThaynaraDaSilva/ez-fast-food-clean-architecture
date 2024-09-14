package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;


public class OrderRepositoryImpl implements OrderRepository {
	
	private final OrderJpaRepository orderJpaRepository;

	

	public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
		this.orderJpaRepository = orderJpaRepository;
	}
	
	
	@Override
	public Order save(Order order) {
        OrderEntity entity = OrderMapper.domainToEntity(order);
        orderJpaRepository.save(entity);
        return order;
    }

	@Override
	public List<Order> findAll() {
        return orderJpaRepository.findAll().stream().map(OrderMapper::entityToDomain)
				.collect(Collectors.toList());
    }



	@Override
	public Order findOrderById(Long id) {
		OrderEntity entity = orderJpaRepository.findById(id).orElse(null);
        return OrderMapper.entityToDomain(entity);
	}


    public List<Order> listUnfinishedOrders() {
        return orderJpaRepository.listUnfinishedOrders().stream()
                .map(OrderMapper::entityToDomain)
                .collect(Collectors.toList());
    }

}
