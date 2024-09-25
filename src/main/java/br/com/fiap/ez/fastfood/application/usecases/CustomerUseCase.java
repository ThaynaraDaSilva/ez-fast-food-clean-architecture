package br.com.fiap.ez.fastfood.application.usecases;

import java.util.Optional;

import java.util.List;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;

public class CustomerUseCase {

	private final CustomerRepository customerRepository;

	public CustomerUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerDTO create(CustomerDTO customerDTO) {

		Customer customer = CustomerMapper.dtoToDomain(customerDTO);

		if (customer != null && customer.isValid()) {

			if (customerRepository.findByCpf(customer.getCpf()) == null) {

				customerRepository.save(customer);

				return customerDTO;
			} else {
				throw new BusinessException("Cliente já cadastrado");
			}
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}

	}

	public CustomerDTO deleteCustomerByCpf(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf);

		if (customer != null) {
			customerRepository.removeByCpf(cpf);
			return CustomerMapper.domainToDto(customer);
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	public CustomerDTO updateCustomer(String cpf, Customer customerToUpdate) {

		Customer existingCustomer = customerRepository.findByCpf(cpf);
		if (existingCustomer != null && customerToUpdate.isValid()) {
			Customer customer = existingCustomer;
			customer.setName(customerToUpdate.getName());
			customer.setEmail(customerToUpdate.getEmail());
			customer.setCpf(customerToUpdate.getCpf());

			customerRepository.save(customer);
			return CustomerMapper.domainToDto(customer);
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	public CustomerDTO findCustomerByCpf(String cpf) {
		CustomerDTO customer = CustomerMapper.domainToDto(customerRepository.findByCpf(cpf));
		if (customer != null) {
			return customer;
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	public Customer authenticate(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf);
		if (customer != null) {
			return customer;
		} else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}

}
