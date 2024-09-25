package br.com.fiap.ez.fastfood.domain.model;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String cpf;
   
    

    public Customer() {
		super();
	}


    public Customer(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }



    public boolean isValid() {
        return cpf != null && !cpf.isEmpty() && email != null && email.contains("@");
    }

    public boolean hasValidCpfFormat() {
        return cpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
