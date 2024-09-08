package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.application.ports.out.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.model.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
	
	private final PaymentJpaRepository paymentJpaRepository;
	
	

	public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository) {
		super();
		this.paymentJpaRepository = paymentJpaRepository;
	}


	@Override
	public Payment save(Payment payment) {
		return paymentJpaRepository.save(payment);
	}


	@Override
	public Payment findPaymentByOrderId(Long orderId) {
		return paymentJpaRepository.findPaymentByOrderId(orderId);
	}

}
