package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
    private String email;
	
	
    
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String cpf, String name, String email) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
