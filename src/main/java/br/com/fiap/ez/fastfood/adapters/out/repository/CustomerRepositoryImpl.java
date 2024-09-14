package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;

public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerJpaRepository customerJpaRepository;

	public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
		this.customerJpaRepository = customerJpaRepository;
	}

	@Override
	public Customer save(Customer customer) {
		CustomerEntity entity = CustomerMapper.domainToEntity(customer);
		customerJpaRepository.save(entity);
		return customer;
	}

	@Override
	public List<Customer> findAll() {
		return customerJpaRepository.findAll().stream().map(CustomerMapper::entityToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Customer removeByCpf(String cpf) {
		customerJpaRepository.removeCustomerByCpf(cpf);
		return null;
	}

	@Override
	public Optional<Customer> findByCpf(String cpf) {
		CustomerEntity entity = customerJpaRepository.findCustomerByCpf(cpf);
		return Optional.ofNullable(entity).map(CustomerMapper::entityToDomain);
	}


}
