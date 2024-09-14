package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;

public class PaymentRepositoryImpl implements PaymentRepository {

	private final PaymentJpaRepository paymentJpaRepository;
	
	public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository) {
	
		this.paymentJpaRepository = paymentJpaRepository;
	}
	
	@Override
	public Payment registerPayment(Payment payment) {
		return paymentJpaRepository.save(payment);
	}

	@Override
	public Payment findPaymentByOrderId(Long orderId) {
		return paymentJpaRepository.findPaymentByOrderId(orderId);
	}

}
