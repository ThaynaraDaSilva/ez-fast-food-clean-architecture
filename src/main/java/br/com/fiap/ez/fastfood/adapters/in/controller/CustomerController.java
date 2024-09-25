package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.application.dto.CustomerResponseDTO;
import br.com.fiap.ez.fastfood.application.dto.LoginDTO;
import br.com.fiap.ez.fastfood.application.usecases.CustomerUseCase;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Operations", description = "Operations related to customers")
public class CustomerController {

	private final CustomerUseCase customerUseCase;

	
	public CustomerController(CustomerUseCase customerUseCase) {
		this.customerUseCase = customerUseCase;
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

		try {
			// Customer customer = customerService.authenticate(loginDTO.getCpf(),loginDTO.getPassword());
			Customer customer = customerUseCase.authenticate(loginDTO.getCpf());
			CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail());
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);

		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	
	@Operation(summary = "Create a new customer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer created"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO createCustomerDTO) {

		try {
			
			CustomerDTO customerDTO = customerUseCase.create(createCustomerDTO);
			
			return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@Operation(summary = "List all customers")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listCustomers() {
		try {
			List<Customer> customers = customerUseCase.listCustomers();
			List<CustomerResponseDTO> customersDTO = new ArrayList<>();
			for (Customer customer : customers) {
				CustomerResponseDTO customerDTO = new CustomerResponseDTO(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail());
				customersDTO.add(customerDTO);
			}
			return new ResponseEntity<>(customersDTO, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "Find customer by CPF")
	@GetMapping(path = "/find-by-cpf/{cpf}", produces = "application/json")
	public ResponseEntity<?> findCustomerByCpf(@PathVariable String cpf) {

		try {
			CustomerDTO customerDTO = customerUseCase.findCustomerByCpf(cpf);
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Hidden
	@Operation(summary = "Delete customer by CPF")
	@DeleteMapping(path = "/delete-by-cpf/{cpf}", produces = "application/json")
	public ResponseEntity<?> deleteCustomerById(@PathVariable String cpf) {

		try {
			customerUseCase.deleteCustomerByCpf(cpf);
			return new ResponseEntity<>(cpf + " removido", HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@Hidden
	@Operation(summary = "Update customer by CPF")
	@PutMapping("/update-by-cpf/{cpf}")
	public ResponseEntity<?> updateCustomer(@PathVariable String cpf, @RequestBody Customer customer) {

		try {
			CustomerDTO customerDTO = customerUseCase.updateCustomer(cpf, customer);

			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
