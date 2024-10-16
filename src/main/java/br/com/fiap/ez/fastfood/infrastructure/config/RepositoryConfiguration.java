package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.adapters.out.repository.JpaCustomerRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.CategoryRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.JpaCategoryRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.JpaOrderRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.OrderRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.JpaPaymentRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.PaymentRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.JpaProductRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.ProductRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public CustomerRepository customerRepository(JpaCustomerRepository customerJpaRepository) {
		return new CustomerRepositoryImpl(customerJpaRepository);
	}

	@Bean
	public CategoryRepository categoryRepository(JpaCategoryRepository categoryJpaRepository) {
		return new CategoryRepositoryImpl(categoryJpaRepository);
	}

	@Bean
	public ProductRepository productRepository(JpaProductRepository productJpaRepository) {
		return new ProductRepositoryImpl(productJpaRepository);
	}

	@Bean
	public OrderRepository orderRepository(JpaOrderRepository orderJpaRepository) {
		return new OrderRepositoryImpl(orderJpaRepository);
	}

	@Bean
	public PaymentRepository paymentRepository(JpaPaymentRepository paymentJpaRepository) {
		return new PaymentRepositoryImpl(paymentJpaRepository);
	}
}
