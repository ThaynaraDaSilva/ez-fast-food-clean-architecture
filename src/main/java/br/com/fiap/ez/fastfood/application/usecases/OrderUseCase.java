package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;

import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class OrderUseCase {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	private final PaymentUseCase paymentUseCase;

	public OrderUseCase(OrderRepository orderRepository, ProductRepository productRepository,
			CustomerRepository customerRepository, PaymentUseCase paymentUseCase) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
		this.paymentUseCase = paymentUseCase;
	}

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		Order saveOrder = new Order();
		Customer customer = customerRepository.findByCpf(createOrderDTO.getCustomerCpf());

		if (customer != null) {
			saveOrder.setCustomer(customer);
		}
		saveOrder.setCustomerName(createOrderDTO.getCustomerName());
		saveOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		saveOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItemList = new ArrayList<>();

		for (OrderItemDTO item : createOrderDTO.getOrderItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = productRepository.findById(item.getProductId())
					.orElseThrow(() -> new BusinessException("Product not found"));

			orderItem.setProduct(product);
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(product.getPrice() * item.getQuantity());
			orderItem.setOrder(saveOrder);
			orderItemList.add(orderItem);
		}

		saveOrder.setOrderItems(orderItemList);
		saveOrder.calculateAndSetTotalPrice();

		Order lastOrder = orderRepository.findLastOrder();

		saveOrder.setOrderNumber(saveOrder.generateOrderNumber(lastOrder));

		Order savedOrder = orderRepository.save(saveOrder);

		
		paymentUseCase.registerPayment(savedOrder);

		
		return OrderMapper.domainToResponseDTO(savedOrder);
	}

	public OrderResponseDTO updateOrderStatus(Long orderId) {
		Order order = orderRepository.findOrderById(orderId);

		if (order.getStatus() == OrderStatus.RECEIVED) {
			order.setStatus(OrderStatus.IN_PREPARATION);
		} else if (order.getStatus() == OrderStatus.IN_PREPARATION) {
			order.setStatus(OrderStatus.READY);
			order.setCompletedTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		} else if (order.getStatus() == OrderStatus.READY) {
			order.setStatus(OrderStatus.COMPLETED);
		} else if (order.getStatus() == OrderStatus.WAITING_PAYMENT) {
			throw new BusinessException("Pedido não pode ser alterado, uma vez que o pagamento ainda não foi confirmado.");
		}else if (order.getStatus() == OrderStatus.CANCELLED) {
			throw new BusinessException("Pedido não pode ser atualizado, uma vez que está cancelado por falta de pagamento.");
		}

		order = orderRepository.save(order);
		return OrderMapper.domainToResponseDTO(order);
	}

	public List<OrderResponseDTO> listUnfinishedOrders() {
		List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();
		return unfinishedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

	public List<OrderResponseDTO> listAllOrders() {
	
		List<Order> orders = orderRepository.findAll();
		if(orders.isEmpty()) {
			throw new BusinessException("Lista de pedidos vazia");
		}
		return orders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}
	
	public List<OrderResponseDTO> listUncompletedOrders() {
		List<Order> uncompletedOrders = orderRepository.listUnCompletedOrders();
		if(!uncompletedOrders.isEmpty()) {
			return uncompletedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
		}else {
			throw new BusinessException("Não há pedidos com status 'Pronto', 'Em preparação' ou 'Recebido'");
		}
		
	}
	
	

}
