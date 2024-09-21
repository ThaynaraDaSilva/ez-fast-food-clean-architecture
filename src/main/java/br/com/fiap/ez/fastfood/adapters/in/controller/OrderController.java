package br.com.fiap.ez.fastfood.adapters.in.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Operations", description = "Operations related to order")
public class OrderController {

	private final OrderUseCase orderUseCase;

	public OrderController(OrderUseCase orderUseCase) {
		this.orderUseCase = orderUseCase;
	}

	@Operation(summary = "Register a new order (fake checkout)")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Order registered"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/checkout", produces = "application/json")
	public ResponseEntity<?> registerOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {

		try {

			return new ResponseEntity<>(orderUseCase.registerOrder(createOrderDTO), HttpStatus.CREATED);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "List all orders")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listOrders() {
		try {
			System.out.println("METODO LIST ALL ODERS -  CONTROLLER");
			return new ResponseEntity<>(orderUseCase.listAllOrders(), HttpStatus.OK);
		} catch (BusinessException e) {
			System.out.println("METODO LIST ALL ODERS -  CONTROLLER EXCEPTION");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "List unfinished orders")

	@GetMapping(path = "/list-orders-in-queue", produces = "application/json")
	public ResponseEntity<?> listUnfinishedOrders() {
		try {
			return new ResponseEntity<>(orderUseCase.listUnfinishedOrders(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(path = "/update-order-status", produces = "application/json")
	public ResponseEntity<?> updateOrderStatus(@RequestBody Long orderId) {
		try {
			return new ResponseEntity<>(orderUseCase.updateOrderStatus(orderId),HttpStatus.OK);
		}catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	
	}

}
