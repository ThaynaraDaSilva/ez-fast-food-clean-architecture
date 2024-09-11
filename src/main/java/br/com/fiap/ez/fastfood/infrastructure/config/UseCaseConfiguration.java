package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.application.usecases.customer.CustomerUseCase;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public CustomerUseCase customerUseCase(CustomerRepository customerRepository) {
		return new CustomerUseCase(customerRepository);
	}

}
