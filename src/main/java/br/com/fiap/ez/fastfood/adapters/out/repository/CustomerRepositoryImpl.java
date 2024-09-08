package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	// 1. implemento query customizada no CustomerJpaRepository
	// 2. Implemento metodo no CustomerRepository
	// 3. Implemento o metodo aqui, pois essa classe extends CustomerRepository

	private final CustomerJpaRepository customerJpaRepository;
	
	/*
	 * @PersistenceContext private EntityManager entityManager;
	 */

	@Autowired
	public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
		this.customerJpaRepository = customerJpaRepository;
	}

	@Override
	public Customer save(Customer customer) {
		return customerJpaRepository.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerJpaRepository.findAll();
	}

	@Override
	public Customer removeByCpf(String cpf) {
		customerJpaRepository.removeCustomerByCpf(cpf);
		return null;
	}

	@Override
	public Customer findCustomerByCpf(String cpf) {
	
		return customerJpaRepository.findCustomerByCpf(cpf);
	}

	@Override
	public Customer updateCustomerByCpf(Customer customer) {
		return customerJpaRepository.save (customer);
	}

	

}
