package br.com.fiap.ez.fastfood.application.serviceremove;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.application.ports.in.OrderService;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.application.ports.out.OrderRepository;
import br.com.fiap.ez.fastfood.application.ports.out.PaymentRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import jakarta.persistence.EnumType;
import br.com.fiap.ez.fastfood.domain.model.OrderItem;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final PaymentRepository paymentRepository;
	private final CustomerRepository customerRepository;
	

	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
			PaymentRepository paymentRepository, CustomerRepository customerRepository) {
		super();
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.paymentRepository = paymentRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {

		Order saveOrder = new Order();
		Customer customer = customerRepository.findCustomerByCpf(createOrderDTO.getCustomerCpf());

		if (customer != null) {
			saveOrder.setCustomer(customer);
		}
		saveOrder.setCustomerName(createOrderDTO.getCustomerName());
		saveOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		saveOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItems = new ArrayList<>();

		if (createOrderDTO.getOrderItems() != null && !createOrderDTO.getOrderItems().isEmpty()) {

			for (OrderItemDTO item : createOrderDTO.getOrderItems()) {

				OrderItem orderItem = new OrderItem();
				Product product = new Product();

				product = productRepository.findById(item.getProductId());

				if (product != null) {
					orderItem.setProduct(product);
				} else {
					throw new BusinessException("Produto não existe");
				}

				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(product.getPrice() * item.getQuantity());
				orderItem.setOrder(saveOrder);
				orderItems.add(orderItem);
			}
			saveOrder.setOrderItems(orderItems);
			saveOrder.calculateAndSetTotalPrice();

			Order order = orderRepository.save(saveOrder);

			registerPayment(order);
			return mapOrderToOrderResponseDTO(order);

		} else {
			throw new BusinessException("Lista de pedidos vazia");
		}

	}

	@Override
	public List<OrderResponseDTO> listUnfinishedOrders() {
		List<Order> orderList = orderRepository.listUnfinishedOrders();
		List<OrderResponseDTO> orderDTOList = new ArrayList<>();

		for (Order order : orderList) {
			OrderResponseDTO orderDTO = new OrderResponseDTO();

			if(order.getCompletedTime() == null) {
				orderDTO.setCompletedTime(null);
			}else {
				
				orderDTO.setCompletedTime(order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
			}
			
			orderDTO.setCustomerCpf(order.getCustomer() != null ? order.getCustomer().getCpf() : "");
			orderDTO.setCustomerName(order.getCustomerName());
			orderDTO.setOrderId(order.getId());

			List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
			for (OrderItem orderItem : order.getOrderItems()) {
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				orderItemDTO.setProductId(orderItem.getProduct().getId());
				orderItemDTO.setQuantity(orderItem.getQuantity());
				orderItemDTOs.add(orderItemDTO);
			}
			orderDTO.setOrderItems(orderItemDTOs);
			orderDTO.setOrderTime(order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
			orderDTO.setOrderStatus(order.getStatus());
			orderDTO.setTotalPrice(order.getTotalPrice());
			if(order.getStatus()!= OrderStatus.WAITING_PAYMENT) {
				orderDTO.setWaitedTime(calculateOrderWaitedTime(order));
			}

			orderDTOList.add(orderDTO);

		}
		return orderDTOList;
	}

	private void registerPayment(Order order) {

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setCustomer(order.getCustomer());
		payment.setPaymentPrice(order.getTotalPrice());
		payment.setPaymentStatus(PaymentStatus.PENDING);

		paymentRepository.save(payment);

	}

	public String calculateOrderWaitedTime(Order order) {
		//LocalDateTime orderLocalDateTime = convertToLocalDateTime(order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
		 ZonedDateTime orderZonedDateTime =order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime  now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

		Duration duration = Duration.between(orderZonedDateTime, now);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		

		return String.format("%02dh%02d", hours, minutes);

		
	}

	/*
	 * private LocalDateTime convertToLocalDateTime(ZonedDateTime dateTimeToConvert)
	 * { return dateTimeToConvert.toLocalDateTime(); }
	 */

	private OrderResponseDTO mapOrderToOrderResponseDTO(Order order) {
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setOrderId(order.getId());

		orderResponseDTO.setOrderTime(order.getOrderTime());

		orderResponseDTO.setTotalPrice(order.getTotalPrice());
		if (order.getCustomer() != null) {
			orderResponseDTO.setCustomerCpf(order.getCustomer().getCpf());
		}
		orderResponseDTO.setCustomerName(order.getCustomerName());
		orderResponseDTO.setOrderStatus(order.getStatus());
		orderResponseDTO.setOrderItems(order.getOrderItems().stream()
				.map(orderItem -> new OrderItemDTO(orderItem.getProduct().getId(), orderItem.getQuantity()))
				.collect(Collectors.toList()));

		return orderResponseDTO;
	}

	@Override
	public List<OrderResponseDTO> listAllOrders() {
		List<Order> orderList = orderRepository.findAll();
		List<OrderResponseDTO> orderDTOList = new ArrayList<>();

		for (Order order : orderList) {
			OrderResponseDTO orderDTO = new OrderResponseDTO();

			
			orderDTO.setCustomerCpf(order.getCustomer() != null ? order.getCustomer().getCpf() : "");
			orderDTO.setCustomerName(order.getCustomerName());
			orderDTO.setOrderId(order.getId());

			List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
			for (OrderItem orderItem : order.getOrderItems()) {
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				orderItemDTO.setProductId(orderItem.getProduct().getId());
				orderItemDTO.setQuantity(orderItem.getQuantity());
				orderItemDTOs.add(orderItemDTO);
			}
			
			orderDTO.setOrderItems(orderItemDTOs);
			
			orderDTO.setOrderTime(order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
			
			if(order.getCompletedTime() == null) {
				orderDTO.setCompletedTime(null);
			}else {
				
				orderDTO.setCompletedTime(order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
			}
			
			if(order.getStatus()!= OrderStatus.WAITING_PAYMENT) {
				orderDTO.setWaitedTime(calculateOrderWaitedTime(order));
			}
			

			orderDTO.setOrderStatus(order.getStatus());
			orderDTO.setTotalPrice(order.getTotalPrice());
		

			orderDTOList.add(orderDTO);

		}
		return orderDTOList;
	}

}
