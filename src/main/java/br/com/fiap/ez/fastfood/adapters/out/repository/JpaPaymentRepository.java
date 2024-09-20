package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.PAYMENT WHERE id = :id")
	PaymentEntity findPaymentById(@Param("id") Long id);
	

}