package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerJpaRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.OrderJpaRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.OrderRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.PaymentJpaRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.PaymentRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.ProductJpaRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.ProductRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;

@Configuration
public class RepositoryConfiguration {
	
	@Bean
    public CustomerRepository customerRepository(CustomerJpaRepository customerJpaRepository) {
        return new CustomerRepositoryImpl(customerJpaRepository);
    }
	
	@Bean
	public ProductRepository productRepository(ProductJpaRepository productJpaRepository) {
		return new ProductRepositoryImpl(productJpaRepository);
	}

    @Bean
    public OrderRepository orderRepository(OrderJpaRepository orderJpaRepository) {
        return new OrderRepositoryImpl(orderJpaRepository);
    }
    
    @Bean
    public PaymentRepository paymentRepository(PaymentJpaRepository paymentJpaRepository) {
        return new PaymentRepositoryImpl(paymentJpaRepository);
    }
}
