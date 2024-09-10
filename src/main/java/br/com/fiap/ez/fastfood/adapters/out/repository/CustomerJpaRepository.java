package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;


public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.CUSTOMER WHERE cpf = :cpf")
	CustomerEntity findCustomerByCpf(@Param("cpf") String cpf);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM EZ_FASTFOOD.CUSTOMER  WHERE CPF = :cpf")
	void removeCustomerByCpf(@Param("cpf") String cpf);

}
