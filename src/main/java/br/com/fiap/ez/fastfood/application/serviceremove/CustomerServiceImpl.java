package br.com.fiap.ez.fastfood.application.serviceremove;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.application.ports.in.CustomerService;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	// private final PasswordEncoder passwordEncoder;

	@Autowired
	/*
	 * public CustomerServiceImpl(CustomerRepository customerRepository,
	 * PasswordEncoder passwordEncoder) { this.customerRepository =
	 * customerRepository; this.passwordEncoder = passwordEncoder; }
	 */

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO createCustomerDTO) {
		Customer customer = convertDtoToCustomer(createCustomerDTO);
		if (customer != null && isCustomerValid(customer)) {
			Customer existingCustomer = findCustomerByCpf(customer.getCpf());
			if (existingCustomer == null) {
				// customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				customerRepository.save(customer);
				return createCustomerDTO;
			} else {
				throw new BusinessException("Cliente já cadastrado");
			}

		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	@Override
	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer deleteCustomerByCpf(String cpf) {

		Customer customer = findCustomerByCpf(cpf);

		if (customer != null) {
			return customerRepository.removeByCpf(cpf);
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	@Transactional
	public Customer updateCustomer(String cpf, Customer updateCustomer) {

		Customer existingCustomer = findCustomerByCpf(cpf);

		if (existingCustomer != null && isCustomerValid(existingCustomer)) {

			existingCustomer.setName(updateCustomer.getName());

			existingCustomer.setEmail(updateCustomer.getEmail());

			existingCustomer.setCpf(updateCustomer.getCpf());

			return customerRepository.updateCustomerByCpf(existingCustomer);
		} else {
			throw new IllegalArgumentException("Dados inválidos.");
		}
	}

	@Override
	public Customer findCustomerByCpf(String cpf) {

		Customer findCustomer = customerRepository.findCustomerByCpf(cpf);

		if (findCustomer != null) {
			return findCustomer;
		} else {
			return null;
		}
	}

	/**
	 * Metodo de autenticacao (cpf e senha) com seguranca implementada Comentado
	 * para utilizacao futura
	 *
	 */
	@Override
	/*
	 * public Customer authenticate(String cpf, String password) { Customer customer
	 * = customerRepository.findCustomerByCpf(cpf);
	 * 
	 * if (customer != null && passwordEncoder.matches(password,
	 * customer.getPassword())) {
	 * 
	 * return customer; }else { throw new BusinessException("CPF ou senha errada.");
	 * } }
	 */

	public Customer authenticate(String cpf) {
		Customer customer = customerRepository.findCustomerByCpf(cpf);
		if (customer != null) {
			return customer;
		} else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}

	public Customer convertDtoToCustomer(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getName(), customerDTO.getCpf(), customerDTO.getEmail());
	}

	public boolean isCustomerValid(Customer customer) {
		if (!customer.getName().equals("string") && !customer.getCpf().equals("string")
				&& !customer.getEmail().equals("string")) {
			return true;
		} else {
			return false;
		}

	}

}
