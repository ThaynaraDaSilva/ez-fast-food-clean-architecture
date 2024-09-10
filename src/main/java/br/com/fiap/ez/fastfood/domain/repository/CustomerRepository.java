package br.com.fiap.ez.fastfood.domain.repository;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

	// Save or update a customer
	Customer save(Customer customer);

	// Find a customer by CPF
	Optional<Customer> findByCpf(String cpf);

	// Delete a customer by CPF
	void removeByCpf(String cpf);

	// Find all customers
	List<Customer> findAll();

}
