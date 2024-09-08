package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.ports.out.OrderRepository;
import br.com.fiap.ez.fastfood.domain.model.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	
	private final OrderJpaRepository orderJpaRepository;

	@Autowired
	public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
		super();
		this.orderJpaRepository = orderJpaRepository;
	}
	
	public Order save(Order order) {
		return orderJpaRepository.save(order);
	}



	@Override
	public List<Order> findAll() {
		return orderJpaRepository.findAll();
	}

	@Override
	public Order updateOrderStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> listUnfinishedOrders() {
		return orderJpaRepository.listUnfinishedOrders();
	}

	@Override
	public Order findOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderJpaRepository.findOrderById(id);
	}

}
