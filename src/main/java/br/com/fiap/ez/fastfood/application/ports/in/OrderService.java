package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;

public interface OrderService {

	OrderResponseDTO registerOrder (CreateOrderDTO orderDTO);
	//Order registerOrder (Order order);
	//Order findOrderById(Long id);
	//List<Order> listOrders();
	//Order updateOrderStatus(Long orderId, String status);
	List<OrderResponseDTO> listUnfinishedOrders();
	List<OrderResponseDTO> listAllOrders();
}
