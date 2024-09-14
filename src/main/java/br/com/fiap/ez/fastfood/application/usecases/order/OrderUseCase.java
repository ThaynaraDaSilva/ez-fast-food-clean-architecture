package br.com.fiap.ez.fastfood.application.usecases.order;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.payment.PaymentUseCase;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
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
	private final PaymentRepository paymentRepository;

	public OrderUseCase(OrderRepository orderRepository, br.com.fiap.ez.fastfood.domain.repository.ProductRepository productRepository2,
			CustomerRepository customerRepository, PaymentRepository paymentRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository2;
		this.customerRepository = customerRepository;
		this.paymentRepository = paymentRepository;
	}
	
	

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		// Create Order entity from DTO

		Customer customer = customerRepository.findByCpf(createOrderDTO.getCustomerCpf())
				.orElseThrow(() -> new BusinessException("Customer not found"));

		Order newOrder = new Order();
		newOrder.setCustomer(customer);
		newOrder.setCustomerName(createOrderDTO.getCustomerName());
		newOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		newOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItems = new ArrayList<>();
		for (OrderItemDTO itemDTO : createOrderDTO.getOrderItems()) {
			Product product = productRepository.findById(itemDTO.getProductId())
					.orElseThrow(() -> new BusinessException("Product not found"));
			OrderItem orderItem = new OrderItem(newOrder, product, itemDTO.getQuantity(),
					product.getPrice() * itemDTO.getQuantity());
			orderItems.add(orderItem);
		}
		newOrder.setOrderItems(orderItems);
		newOrder.calculateAndSetTotalPrice();

		Order savedOrder = orderRepository.save(newOrder);

		// Register payment for the order
		paymentRepository.registerPayment(savedOrder);

		// Map order to response DTO using OrderMapper
		return OrderMapper.domainToResponseDTO(savedOrder);
	}

	public List<OrderResponseDTO> listUnfinishedOrders() {
		List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();
		return unfinishedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

	public List<OrderResponseDTO> listAllOrders() {
		List<Order> allOrders = orderRepository.findAll();
		return allOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

}
