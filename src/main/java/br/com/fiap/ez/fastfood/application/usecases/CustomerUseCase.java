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

		// Convert DTO to Domain Model using CustomerMapper
		Customer customer = CustomerMapper.dtoToDomain(customerDTO);

		if (customer != null && customer.isValid()) {
			
			//Customer existingCustomer = customerRepository.findByCpf(customer.getCpf());
			if (customerRepository.findByCpf(customer.getCpf()) ==  null) {
				// customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				
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
		//Customer updatedCustomer = CustomerMapper.dtoToDomain(updateCustomerDTO);

		Customer existingCustomer = customerRepository.findByCpf(cpf);
		if (existingCustomer != null && customerToUpdate.isValid()) {
			Customer customer = existingCustomer;
			customer.setName(customerToUpdate.getName());
			customer.setEmail(customerToUpdate.getEmail());
			customer.setCpf(customerToUpdate.getCpf());

			// Update the customer in the repository
			customerRepository.save(customer);
			return CustomerMapper.domainToDto(customer); // Return updated customer as DTO
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	public CustomerDTO findCustomerByCpf(String cpf) {
		CustomerDTO customer = CustomerMapper.domainToDto(customerRepository.findByCpf(cpf));
		if(customer!=null) {
			return customer;
		}else {
			throw new BusinessException("Cliente não encontrado");
		}
	}
	
	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	public Customer authenticate(String cpf) {
		Customer customer =  customerRepository.findByCpf(cpf);
		if(customer!=null) {
			return customer;
		}else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}

}
