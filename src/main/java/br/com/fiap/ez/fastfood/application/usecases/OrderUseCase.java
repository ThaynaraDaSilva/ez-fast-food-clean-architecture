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
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import jakarta.transaction.Transactional;

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
			CustomerRepository customerRepository, PaymentUseCase paymentUseCase) { // Inject PaymentUseCase here
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
		this.paymentUseCase = paymentUseCase; // Assign it here
	}

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		// Create Order entity from DTO
		Order saveOrder = new Order();
		Customer customer = customerRepository.findByCpf(createOrderDTO.getCustomerCpf())
				.orElseThrow(() -> new BusinessException("Customer not found"));

		saveOrder.setCustomer(customer);
		saveOrder.setCustomerName(createOrderDTO.getCustomerName());
		saveOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		saveOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItemList = new ArrayList<>();

		for (OrderItemDTO item : createOrderDTO.getOrderItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = productRepository.findById(item.getProductId())
					.orElseThrow(() -> new BusinessException("Product not found"));

			// Set the product, quantity, and price
			orderItem.setProduct(product);
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(product.getPrice() * item.getQuantity());

			// Set the order reference in the order item
			orderItem.setOrder(saveOrder);
			// Add the order item to the list
			orderItemList.add(orderItem);
		}

		// Set the order items list in the order
		saveOrder.setOrderItems(orderItemList);

		// Calculate and set the total price
		saveOrder.calculateAndSetTotalPrice();
		Order lastOrder = orderRepository.findLastOrder();
		saveOrder.setOrderNumber(saveOrder.generateOrderNumber(lastOrder.getOrderTime(),lastOrder.getOrderNumber()));

		// Register payment for the order
		paymentUseCase.registerPayment(orderRepository.save(saveOrder));

		// Map order to response DTO using OrderMapper
		return OrderMapper.domainToResponseDTO(saveOrder);
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
