package br.com.fiap.ez.fastfood.domain.repository;


import br.com.fiap.ez.fastfood.domain.model.Customer;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

public interface CustomerRepository {
	
	Customer save(Customer customer);
    List <Customer> findAll();
	Customer removeByCpf(String cpf);
	Optional<Customer> findByCpf(String cpf);	
}
