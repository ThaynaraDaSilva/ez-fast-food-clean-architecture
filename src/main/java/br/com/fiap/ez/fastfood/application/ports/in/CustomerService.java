package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
/**
 Responsavel por definir os metodos de logica de negocio
 */
public interface CustomerService {

	CustomerDTO createCustomer(CustomerDTO customerDTO);
	Customer updateCustomer(String cpf, Customer updateCustomer);
    List<Customer> listCustomers();
    Customer deleteCustomerByCpf(String cpf);
    Customer findCustomerByCpf (String cpf);
    //Customer authenticate(String cpf, String password);
    Customer authenticate(String cpf);
}
