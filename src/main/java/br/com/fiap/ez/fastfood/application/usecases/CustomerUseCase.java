package br.com.fiap.ez.fastfood.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.application.dto.CreateCustomerDTO;
import br.com.fiap.ez.fastfood.application.dto.CustomerResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;

public class CustomerUseCase {

	private final CustomerRepository customerRepository;

	public CustomerUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerResponseDTO create(CreateCustomerDTO customerDTO) {

		Customer customer = CustomerMapper.dtoToDomain(customerDTO);

		if (customer != null && customer.isValid()) {

			if (customerRepository.findByCpf(customer.getCpf()) == null) {
				return CustomerMapper.domainToResponseDto(customerRepository.save(customer));
			} else {
				throw new BusinessException("Cliente já cadastrado");
			}
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}

	}

	public CustomerResponseDTO deleteCustomerByCpf(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf);

		if (customer != null) {
			customerRepository.removeByCpf(cpf);
			return CustomerMapper.domainToResponseDto(customer);
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	public CustomerResponseDTO updateCustomer(String cpf, Customer customerToUpdate) {

		Customer existingCustomer = customerRepository.findByCpf(cpf);
		if (existingCustomer != null && customerToUpdate.isValid()) {
			Customer customer = existingCustomer;
			customer.setName(customerToUpdate.getName());
			customer.setEmail(customerToUpdate.getEmail());
			customer.setCpf(customerToUpdate.getCpf());

			customerRepository.save(customer);
			return CustomerMapper.domainToResponseDto(customer);
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	public CustomerResponseDTO findCustomerByCpf(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf);
		if (customer != null) {
			CustomerResponseDTO customerDTO = CustomerMapper.domainToResponseDto(customerRepository.findByCpf(cpf));
			return customerDTO;
		} else {
			throw new BusinessException("Cliente não encontrado");
		}	       
	}

	public List<CustomerResponseDTO> listCustomers() {

		List<Customer> customers = customerRepository.findAll();
		if(!customers.isEmpty()) {
			return customers.stream().map(CustomerMapper::domainToResponseDto).collect(Collectors.toList());
		}else {
		throw new BusinessException("Não há clientes cadastrados.");
		}
		
	}

	public CustomerResponseDTO authenticate(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf);
		if (customer != null) {
			return CustomerMapper.domainToResponseDto(customer);
		} else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}

}
