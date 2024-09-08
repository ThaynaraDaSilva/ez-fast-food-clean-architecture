package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;



public interface OrderRepository {
	
	//OrderDTO  save(OrderDTO Order);
	Order save(Order Order);
    List <Order> findAll();
	//List <OrderResponseDTO> findAlll();
	Order updateOrderStatus(String status);
    List <Order> listUnfinishedOrders();
    Order findOrderById(Long id);
}
