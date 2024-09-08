package br.com.fiap.ez.fastfood.application.ports.in;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;

public interface PaymentService {
	
	void makePayment(PaymentDTO paymentDTO, Long id);

}
