package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.domain.model.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.PAYMENT WHERE order_id = :id")
	Payment findPaymentByOrderId(@Param("id") Long id);
	

}
