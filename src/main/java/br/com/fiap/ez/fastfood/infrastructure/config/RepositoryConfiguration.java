package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerJpaRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerRepositoryImpl;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;

@Configuration
public class RepositoryConfiguration {
	
	@Bean
    public CustomerRepository customerRepository(CustomerJpaRepository customerJpaRepository) {
        return new CustomerRepositoryImpl(customerJpaRepository);
    }

}
