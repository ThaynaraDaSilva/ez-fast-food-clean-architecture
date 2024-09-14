package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.application.usecases.customer.CustomerUseCase;
import br.com.fiap.ez.fastfood.application.usecases.order.OrderUseCase;
import br.com.fiap.ez.fastfood.application.usecases.payment.PaymentUseCase;
import br.com.fiap.ez.fastfood.application.usecases.product.ProductUseCase;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public CustomerUseCase customerUseCase(CustomerRepository customerRepository) {
		return new CustomerUseCase(customerRepository);
	}

	@Bean
	public ProductUseCase productUseCase(ProductRepository productRepository) {
		return new ProductUseCase(productRepository);
	}
	
	@Bean
	public PaymentUseCase paymentUseCase(PaymentRepository paymentRepository) {
	    return new PaymentUseCase(paymentRepository);
	}


	@Bean
	public OrderUseCase orderUseCase(OrderRepository orderRepository, ProductRepository productRepository,
			CustomerRepository customerRepository, PaymentRepository paymentRepository) {
		return new OrderUseCase(orderRepository,productRepository,customerRepository,paymentRepository);
	}

}
