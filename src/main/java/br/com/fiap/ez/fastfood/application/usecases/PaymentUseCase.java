package br.com.fiap.ez.fastfood.application.usecases;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;

public class PaymentUseCase {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;

	public PaymentUseCase(PaymentRepository paymentRepository, OrderRepository orderRepository) {
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;

	}

	/*
	 * public PaymentUseCase(PaymentRepository paymentRepository) {
	 * this.paymentRepository = paymentRepository; }
	 */
	public void registerPayment(Order order) {

		Payment payment = new Payment();

		payment.setOrder(order);

		payment.setCustomer(order.getCustomer());
		payment.setPaymentPrice(order.getTotalPrice());
		payment.setPaymentStatus(PaymentStatus.PENDING);
		paymentRepository.registerPayment(payment);

	}

	public PaymentDTO registerPaymentStatus(PaymentDTO paymentDto) {
		Payment payment = paymentRepository.findPaymentById(paymentDto.getPaymentId());
		Order order = orderRepository.findOrderById(payment.getOrder().getId());
		
		//payment
		payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		payment.setPaymentStatus(PaymentStatus.valueOf(paymentDto.getPaymentStatus().toUpperCase()));
		paymentRepository.registerPaymentStatus(payment);
		
		//order
	    if(PaymentStatus.valueOf(paymentDto.getPaymentStatus().toUpperCase()) == PaymentStatus.OK) {
	    	order.setStatus(OrderStatus.RECEIVED);
	    	order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
	    	orderRepository.save(order);
	    }
		
		return PaymentMapper.domainToResponseDto(payment);

	}
}
