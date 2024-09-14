package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;

public class CustomerMapper {

	// Convert Domain Model to Persistence Entity
	public static CustomerEntity domainToEntity(Customer customer) {
		if (customer == null) {
			return null;
		}
		CustomerEntity entity = new CustomerEntity();
		entity.setId(customer.getId());
		entity.setName(customer.getName());
		entity.setEmail(customer.getEmail());
		entity.setCpf(customer.getCpf());
		return entity;
	}

	// Convert Persistence Entity to Domain Model
	public static Customer entityToDomain(CustomerEntity entity) {
		if (entity == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setId(entity.getId());
		customer.setCpf(entity.getCpf());
		customer.setName(entity.getName());
		customer.setEmail(entity.getEmail());
		return customer;
	}

	// Convert DTO to Domain Model
	public static Customer dtoToDomain(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setCpf(customerDTO.getCpf());
		System.out.println("CPF: " + customerDTO.getCpf());
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		return customer;
	}

	// Convert Domain Model to DTO (if needed)
	public static CustomerDTO domainToDto(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setCpf(customer.getCpf());
		return customerDTO;
	}
}
