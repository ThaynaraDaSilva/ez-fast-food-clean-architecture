package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;

public class PaymentUseCase {

    private final PaymentRepository paymentRepository;

    public PaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void registerPayment(Order order) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCustomer(order.getCustomer());
        payment.setPaymentPrice(order.getTotalPrice());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.registerPayment(payment);
    }
}

