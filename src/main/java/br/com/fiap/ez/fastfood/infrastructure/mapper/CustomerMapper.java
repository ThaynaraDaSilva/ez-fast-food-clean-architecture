package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;

public class CustomerMapper {

	// Convert Domain Model to Persistence Entity
	public static CustomerEntity domainToEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(customer.getId());
		entity.setName(customer.getName());
		entity.setEmail(customer.getEmail());
		entity.setCpf(customer.getCpf());
		return entity;
	}

	// Convert Persistence Entity to Domain Model
	public static Customer entityToDomain(CustomerEntity entity) {
		return new Customer(entity.getId(), entity.getName(), entity.getEmail(), entity.getCpf());
	}

	// Convert DTO to Domain Model
	public static Customer dtoToDomain(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getCpf());
	}

	// Convert Domain Model to DTO (if needed)
	public static CustomerDTO domainToDto(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setCpf(customer.getCpf());
		return customerDTO;
	}
}
