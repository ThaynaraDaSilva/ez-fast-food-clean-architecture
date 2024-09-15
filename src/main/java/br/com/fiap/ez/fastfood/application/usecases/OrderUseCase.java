package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.ProductMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderUseCase {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	// private final ProductUseCase productUseCase;
	private final CustomerRepository customerRepository;
	private final PaymentRepository paymentRepository;
	// private final CustomerUseCase customerUseCase;
	// private final PaymentUseCase paymentUseCase;

	public OrderUseCase(OrderRepository orderRepository, ProductRepository productRepository,
			CustomerRepository customerRepository,PaymentRepository paymentRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
		this.paymentRepository = paymentRepository;
	}

	/*
	 * public OrderUseCase(OrderRepository orderRepository, ProductRepository
	 * productRepository, ProductUseCase productUseCase, CustomerRepository
	 * customerRepository, PaymentUseCase paymentUseCase) { this.orderRepository =
	 * orderRepository; this.productRepository = productRepository;
	 * this.productUseCase = productUseCase; this.customerRepository =
	 * customerRepository; this.paymentUseCase = paymentUseCase; }
	 */

	/*
	 * public OrderUseCase(OrderRepository orderRepository, ProductUseCase
	 * productUseCase, CustomerUseCase customerUseCase, PaymentUseCase
	 * paymentUseCase) { // Inject PaymentUseCase here this.orderRepository =
	 * orderRepository; this.productUseCase = productUseCase; this.customerUseCase =
	 * customerUseCase; this.paymentUseCase = paymentUseCase; // Assign it here }
	 */

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {

		// return OrderMapper.domainToResponseDTO(order);
		return null;
	}

	public List<OrderResponseDTO> listUnfinishedOrders() {
		List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();
		return unfinishedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

	public List<OrderResponseDTO> listAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

}
