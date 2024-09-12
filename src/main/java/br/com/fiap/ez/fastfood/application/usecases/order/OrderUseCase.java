package br.com.fiap.ez.fastfood.application.usecases.customer;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.application.ports.out.OrderRepository;
import br.com.fiap.ez.fastfood.application.ports.out.PaymentRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    public OrderUseCase(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        PaymentRepository paymentRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
        // Create Order entity from DTO
        Customer customer = customerRepository.findCustomerByCpf(createOrderDTO.getCustomerCpf())
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
            OrderItem orderItem = new OrderItem(newOrder, product, itemDTO.getQuantity(), product.getPrice() * itemDTO.getQuantity());
            orderItems.add(orderItem);
        }
        newOrder.setOrderItems(orderItems);
        newOrder.calculateAndSetTotalPrice();

        Order savedOrder = orderRepository.save(newOrder);

        // Register payment for the order
        registerPayment(savedOrder);

        // Map order to response DTO using OrderMapper
        return OrderMapper.entityToDomain(savedOrder);
    }

    public List<OrderResponseDTO> listUnfinishedOrders() {
        List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();
        return unfinishedOrders.stream()
                .map(OrderMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> listAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream()
                .map(OrderMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    private void registerPayment(Order order) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCustomer(order.getCustomer());
        payment.setPaymentPrice(order.getTotalPrice());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);
    }

    private String calculateOrderWaitedTime(Order order) {
        ZonedDateTime orderTime = order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        Duration duration = Duration.between(orderTime, now);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02dh%02d", hours, minutes);
    }

    private OrderResponseDTO mapOrderToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCustomerCpf(order.getCustomer() != null ? order.getCustomer().getCpf() : "");
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderStatus(order.getStatus());

        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(item.getProduct().getId(), item.getQuantity()))
                .collect(Collectors.toList());

        dto.setOrderItems(itemDTOs);
        if (order.getCompletedTime() != null) {
            dto.setCompletedTime(order.getCompletedTime());
        }

        if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
            dto.setWaitedTime(calculateOrderWaitedTime(order));
        }

        return dto;
    }
}
