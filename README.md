# ez-fast-food-clean-arch
Tech Challenge 2 - Clean Architecture e Kubernetes


# Estrutura Hexagonal
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   │       ├── CustomerController.java
│   └── out
│       └── repository
│           ├── CustomerRepositoryImpl.java
│           └── JpaCustomerRepository.java
├── application
│   ├── ports
│   │   ├── in
│   │   │   └── CustomerService.java
│   │   └── out
│   │       └── CustomerRepository.java
│   ├── service
│   │   └── CustomerServiceImpl.java
│   └── dto
│       └── CustomerDTO.java
├── config
│   └── exception
│       └── CustomExceptionHandler.java
|        └── ErrorResponse.java
│   └── security
│       └── SecurityConfig.java
│        OpenApiConfig.java
├── domain
│   └── model
│       └── Customer.java
├── APIApplication.java


# Estrutura Limpa

br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   │       └── CustomerController.java         # Input adapters, handles HTTP requests
│   └── out
│       └── repository
│           └── JpaCustomerRepository.java      # Repository implementation (ORM-specific)
           └── CustomerRepositoryImpl.java     # Implementation of outbound port
├── application
│   ├── usecases
│   │   ├── customer
│   │   │   └── CreateCustomerUseCase.java      # Use case for creating customers
│   │   │   └── UpdateCustomerUseCase.java      # Use case for updating customers
│   │   │   └── DeleteCustomerUseCase.java      # Use case for deleting customers
│   │   │   └── GetCustomerUseCase.java         # Use case for fetching customers
│   │   └── dto
│   │       └── CustomerDTO.java                # Data transfer object for customer
│   ├── ports
│   │   ├── in
│   │   │   └── CreateCustomer.java             # Input port, defines use case contract for customer creation
│   │   │   └── UpdateCustomer.java             # Input port, defines use case contract for customer update
│   │   │   └── DeleteCustomer.java             # Input port, defines use case contract for customer deletion
│   │   │   └── GetCustomer.java                # Input port, defines use case contract for fetching customers
│   │   └── out
│   │       └── CustomerRepository.java         # Outbound port, interface for repository actions
├── domain
│   └── model
│       └── Customer.java                       # Domain model, holds core business rules for customer
├── frameworks
│   ├── config
│   │   └── ApiApplication.java                 # Main entry point
│   │   └── OpenApiConfig.java                  # Swagger/OpenAPI configuration
│   │   └── SecurityConfig.java                 # Security-related configurations
│   ├── exception
│   │   └── CustomExceptionHandler.java         # Global exception handling
│   │   └── ErrorResponse.java                  # Error response object







