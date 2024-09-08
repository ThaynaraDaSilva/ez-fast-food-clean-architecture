package br.com.fiap.ez.fastfood.application.ports.out;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import java.util.List;

/**
Responsavel por definir os metodos de acesso a dados
Foco em operacoes CRUD e acesso a dados
*/
public interface CustomerRepository {
	
	Customer save(Customer customer);
    List <Customer> findAll();
	Customer removeByCpf(String cpf);
	Customer findCustomerByCpf (String cpf);
	Customer updateCustomerByCpf(Customer customer);
	
	
}
