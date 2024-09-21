package br.com.fiap.ez.fastfood.domain.repository;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Order;

public interface OrderRepository {

	Order save(Order Order);
	List<Order> findAll();
	List<Order> listUnfinishedOrders();
	Order findOrderById(Long id);
	Order findLastOrder();
	
	List<Order> listUnCompletedOrders();
}
