package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.ez.fastfood.application.dto.CreateCustomerDTO;
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

		return new ResponseEntity<>(customerUseCase.authenticate(loginDTO.getCpf()), HttpStatus.OK);

	}

	@Operation(summary = "Create a new customer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer created"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {

		//CustomerResponseDTO customerDTO = customerUseCase.create(createCustomerDTO);

		return new ResponseEntity<>(customerUseCase.create(createCustomerDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "List all customers")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listCustomers() {
	
		return new ResponseEntity<>(customerUseCase.listCustomers(), HttpStatus.OK);
	}

	@Operation(summary = "Find customer by CPF")
	@GetMapping(path = "/find-by-cpf/{cpf}", produces = "application/json")
	public ResponseEntity<?> findCustomerByCpf(@PathVariable String cpf) {
		
		return new ResponseEntity<>(customerUseCase.findCustomerByCpf(cpf), HttpStatus.OK);

	}

	@Hidden
	@Operation(summary = "Delete customer by CPF")
	@DeleteMapping(path = "/delete-by-cpf/{cpf}", produces = "application/json")
	public ResponseEntity<?> deleteCustomerById(@PathVariable String cpf) {

		return new ResponseEntity<>(customerUseCase.deleteCustomerByCpf(cpf), HttpStatus.OK);
		
	}

	@Hidden
	@Operation(summary = "Update customer by CPF")
	@PutMapping("/update-by-cpf/{cpf}")
	public ResponseEntity<?> updateCustomer(@PathVariable String cpf, @RequestBody Customer customer) {
		
		return new ResponseEntity<>(customerUseCase.updateCustomer(cpf, customer), HttpStatus.OK);

	}

}
