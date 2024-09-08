package br.com.fiap.ez.fastfood.application.ports.out;

import br.com.fiap.ez.fastfood.domain.model.Payment;

public interface PaymentRepository {
	
	Payment save(Payment payment);
	Payment findPaymentByOrderId(Long orderId);

}
