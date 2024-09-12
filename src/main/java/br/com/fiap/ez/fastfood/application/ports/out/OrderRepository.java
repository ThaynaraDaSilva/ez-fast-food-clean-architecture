package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Order;

public interface OrderRepository {

	Order save(Order Order);
	List<Order> findAll();
	Order updateOrderStatus(String status);
	List<Order> listUnfinishedOrders();
	Order findOrderById(Long id);

}
