package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.ProductMapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderUseCase {

	private final OrderRepository orderRepository;
	// private final ProductRepository productRepository;
	private final ProductUseCase productUseCase;
	//private final CustomerRepository customerRepository;
	private final CustomerUseCase customerUseCase;
	private final PaymentUseCase paymentUseCase;

	/*
	 * public OrderUseCase(OrderRepository orderRepository, ProductRepository
	 * productRepository,ProductUseCase productUseCase, CustomerRepository
	 * customerRepository, PaymentUseCase paymentUseCase) { // Inject PaymentUseCase
	 * here this.orderRepository = orderRepository; this.productRepository =
	 * productRepository; this.productUseCase = productUseCase;
	 * this.customerRepository = customerRepository; this.paymentUseCase =
	 * paymentUseCase; // Assign it here }
	 */
	public OrderUseCase(OrderRepository orderRepository, ProductUseCase productUseCase,
			CustomerUseCase customerUseCase, PaymentUseCase paymentUseCase) { // Inject PaymentUseCase here
		this.orderRepository = orderRepository;
		this.productUseCase = productUseCase;
		this.customerUseCase = customerUseCase;
		this.paymentUseCase = paymentUseCase; // Assign it here
	}

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		// Create Order entity from DTO

		Order order = new Order();
	
		List<OrderItem> orderItems = new ArrayList<>();

		Customer customer = CustomerMapper.dtoToDomain(customerUseCase.findCustomerByCpf(createOrderDTO.getCustomerCpf()));
		
		if (customer != null) {
			order.setCustomer(customer);
		}
	
		order.setCustomerName(createOrderDTO.getCustomerName());
		order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		order.setStatus(OrderStatus.WAITING_PAYMENT);

		System.out.println("Antes do for");
		
		for (OrderItemDTO item : createOrderDTO.getOrderItems()) {
			
			OrderItem orderItem = new OrderItem();
			
			Optional<Product> product = productUseCase.findProductById(item.getProductId());
			
			if (product != null) {
				orderItem.setProduct(product.get());
				System.out.println("Entrei no if" + orderItem.getProduct().getId());
			} else {
				throw new BusinessException("É obrigatório ter ao menos um produto");
			}
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(product.get().getPrice() * item.getQuantity());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}

		order.setOrderItems(orderItems);
		order.calculateAndSetTotalPrice();

		// Save order and register payment
		paymentUseCase.registerPayment(orderRepository.save(order));

		// Map order to response DTO using OrderMapper
		return OrderMapper.domainToResponseDTO(order);
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
