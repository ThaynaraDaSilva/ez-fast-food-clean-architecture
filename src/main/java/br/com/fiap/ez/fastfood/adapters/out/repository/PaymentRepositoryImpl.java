package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;

public class PaymentRepositoryImpl implements PaymentRepository {

	private final JpaPaymentRepository jpaPaymentRepository;

	public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository) {

		this.jpaPaymentRepository = jpaPaymentRepository;
	}

	@Override
	public Payment registerPayment(Payment payment) {
		PaymentEntity entity = PaymentMapper.domainToEntity(payment);
		jpaPaymentRepository.save(entity);
		return payment;
	}

	@Override
	public Payment findPaymentByOrderId(Long orderId) {
		PaymentEntity entity =  jpaPaymentRepository.findPaymentByOrderId(orderId);
		return PaymentMapper.entityToDomain(entity);
	}

	@Override
	public Payment registerPaymentStatus(Payment payment) {
		PaymentEntity entity =  PaymentMapper.domainToEntity(payment);
		jpaPaymentRepository.save(entity);
		return payment;
		
	}

}
