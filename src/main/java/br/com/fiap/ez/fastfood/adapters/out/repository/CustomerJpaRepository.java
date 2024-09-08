package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.CUSTOMER WHERE cpf = :cpf")
	Customer findCustomerByCpf(@Param("cpf") String cpf);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM EZ_FASTFOOD.CUSTOMER  WHERE CPF = :cpf")
	void removeCustomerByCpf(@Param("cpf") String cpf);

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(nativeQuery = true, value =
	 * "UPDATE CUSTOMERS SET name = :name, email = :email WHERE cpf = :cpf") void
	 * updateCustomerByCpf(@Param("cpf") String cpf, @Param("name") String
	 * name, @Param("email") String email);
	 */
}
