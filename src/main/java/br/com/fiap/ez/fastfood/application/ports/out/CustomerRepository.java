package br.com.fiap.ez.fastfood.application.ports.out;


import br.com.fiap.ez.fastfood.domain.model.Customer;
import java.util.List;
import java.util.Optional;

/**
Responsavel por definir os metodos de acesso a dados
Foco em operacoes CRUD e acesso a dados
*/
public interface CustomerRepository {
	
	Customer save(Customer customer);
    List <Customer> findAll();
	Customer removeByCpf(String cpf);
	Optional<Customer> findByCpf(String cpf);
	Customer updateCustomerByCpf(Customer customer);
	
	
}
