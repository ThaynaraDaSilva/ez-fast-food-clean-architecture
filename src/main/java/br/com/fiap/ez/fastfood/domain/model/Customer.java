package br.com.fiap.ez.fastfood.domain.model;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String cpf;
   

    // Constructor for creating a new Customer (ID not required for new creation)
    public Customer(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    // Constructor for existing Customer with ID (used for updates or retrieval)
    public Customer(Long id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    // Business logic method: Validation inside the domain entity
    public boolean isValid() {
        return cpf != null && !cpf.isEmpty() && email != null && email.contains("@");
    }

    // Example of business logic related to CPF format
    public boolean hasValidCpfFormat() {
        return cpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$");
    }

    // Getters and setters
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
