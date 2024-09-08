package br.com.fiap.ez.fastfood.adapters.in.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ez.fastfood.application.ports.in.PaymentService;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Operations", description = "Operations related to order payment")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/make-payment")
	public ResponseEntity<?> makePayment(@RequestBody PaymentDTO paymentDTO) {
		try {
			paymentService.makePayment(paymentDTO, paymentDTO.getOrderId());
			return ResponseEntity.ok().build();
		}catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	
	}

}
