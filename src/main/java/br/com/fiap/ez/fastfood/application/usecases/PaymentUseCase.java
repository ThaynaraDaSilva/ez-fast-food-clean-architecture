package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;

public class PaymentUseCase {

    private final PaymentRepository paymentRepository;
    //private final OrderRepository orderRepository;

	/*
	 * public PaymentUseCase(PaymentRepository paymentRepository, OrderRepository
	 * orderRepository) { this.paymentRepository = paymentRepository;
	 * this.orderRepository = orderRepository; }
	 */
    public PaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
       
    }

    public void registerPayment(Order order) {
    	//Order savedOrder = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
    	//Order savedOrder = orderRepository.findById(payment.getOrder().getId()).orElseThrow(() -> new BusinessException("Order not found"));
        Payment payment = new Payment();
        
        System.out.println("====================ANTES DO SAVE PAYMENT NO USE CASE ======================");
        System.out.println("ORDER ID " + order.getId());
        System.out.println("==========================================");

        
        payment.setOrder(order);
       
        
        payment.setCustomer(order.getCustomer());
        payment.setPaymentPrice(order.getTotalPrice());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.registerPayment(payment);
        System.out.println("====================APOS DO SAVE PAYMENT NO USE CASE ======================");
    }
}

