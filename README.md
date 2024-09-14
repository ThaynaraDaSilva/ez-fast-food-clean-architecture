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
│   │       └── CustomerController.java       # Input adapter, handles HTTP requests
│   └── out
│       └── repository
│           ├── CustomerRepositoryImpl.java   # Repository implementation (converts domain to persistence entities)
│           └── JpaCustomerRepository.java    # JPA-specific repository interface
├── application
│   ├── usecases
│   │   └── CustomerUseCase.java              # Centralized use case for CRUD operations
│   └── dto
│       └── CustomerDTO.java                  # DTO for customer with validation annotations
├── domain
│   └── model
│       └── Customer.java                     # Pure domain entity without JPA/validation annotations
│   └── repository
│       └── CustomerRepository.java           # Domain repository interface (abstraction for persistence)
├── infrastructure
│   ├── persistence
│   │   └── CustomerEntity.java               # Persistence-specific entity with JPA annotations
│   └── mapper
│       └── CustomerMapper.java               # Mapper to convert between domain and persistence entities
├── config
│   └── exception
│       └── CustomExceptionHandler.java       # Global exception handler
│       └── ErrorResponse.java                # Error response object
│   └── security
│       └── SecurityConfig.java               # Security configurations
│       └── OpenApiConfig.java                # Swagger/OpenAPI configuration
├── APIApplication.java                       # Main entry point for Spring Boot

10/09/2024

br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   └── out
├── application
│   ├── usecases
│   └── dto
├── domain
│   └── model
│   └── repository
├── infrastructure
│   ├── persistence
│   ├── mapper
│   └── config
│       └── UseCaseConfiguration.java         # Bean configuration for use cases
│       └── RepositoryConfiguration.java      # Bean configuration for repositories
├── config
│   └── exception
│   └── security
├── APIApplication.java




Explanation of Each Section:
1. Adapters
In (Controller Layer):
CustomerController.java: Handles incoming HTTP requests, interacts with the use cases, and returns responses.
Out (Repository Layer):
CustomerRepositoryImpl.java: Implements the CustomerRepository interface from the domain layer. It converts between domain entities and persistence entities using a mapper.
JpaCustomerRepository.java: The actual JPA repository interface used for database interactions.
2. Application
Use Cases:
CustomerUseCase.java: The centralized use case that handles CRUD operations for customers. It interacts with the CustomerRepository and performs business logic.
DTOs:
CustomerDTO.java: Used for data transfer and validation. Contains validation annotations like @NotNull, @Email, and @Pattern for validating input data before it reaches the use case.
3. Domain
Model:
Customer.java: The domain entity representing a customer. It contains business logic and rules but is not tied to any persistence or validation frameworks (i.e., no JPA or validation annotations).
Repository:
CustomerRepository.java: The interface that abstracts the persistence layer. The use case depends on this interface to persist or retrieve customer data, adhering to Dependency Inversion Principle (DIP).
4. Infrastructure
Persistence Entities:
CustomerEntity.java: The entity that contains the JPA annotations (like @Entity, @Table, @Id) for persistence purposes. This entity is different from the domain entity (Customer.java).
Mappers:
CustomerMapper.java: This class is responsible for converting domain entities to persistence entities (and vice versa). This separation of concerns ensures the domain layer remains pure and unaware of infrastructure concerns like JPA.
5. Config
Exception Handling:
CustomExceptionHandler.java: A global exception handler that catches and returns standardized error responses.
Security:
SecurityConfig.java: Handles security configurations like authentication and authorization.
OpenAPI:
OpenApiConfig.java: Configures Swagger or OpenAPI for API documentation.
6. APIApplication.java
This is the main class that starts the Spring Boot application.



14/09/2024

br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   │       └── CustomerController.java       # Input adapter, handles HTTP requests
│   │       └── OrderController.java          # Order-related HTTP requests
│   └── out
│       └── repository
│           ├── CustomerRepositoryImpl.java   # Repository implementation (converts domain to persistence entities)
│           ├── OrderRepositoryImpl.java      # Repository implementation for Order
│           └── JpaCustomerRepository.java    # JPA-specific repository interface for Customer
│           └── JpaOrderRepository.java       # JPA-specific repository interface for Order
│
├── application
│   ├── usecases
│   │   ├── CustomerUseCase.java              # Centralized use case for CRUD operations for Customer
│   │   └── OrderUseCase.java                 # Centralized use case for order operations
│   └── dto
│       ├── CustomerDTO.java                  # DTO for customer with validation annotations
│       ├── CreateOrderDTO.java               # DTO for order creation
│       ├── OrderResponseDTO.java             # DTO for order response
│       └── ProductDTO.java                   # DTO for Product
│
├── domain
│   ├── model
│   │   ├── Customer.java                     # Pure domain entity without JPA/validation annotations
│   │   ├── Order.java                        # Pure domain entity for Order
│   │   └── Product.java                      # Pure domain entity for Product
│   └── repository
│       ├── CustomerRepository.java           # Domain repository interface (abstraction for persistence)
│       └── OrderRepository.java              # Domain repository interface for Order
│
├── infrastructure
│   ├── persistence
│   │   ├── CustomerEntity.java               # Persistence-specific entity with JPA annotations for Customer
│   │   ├── OrderEntity.java                  # Persistence-specific entity with JPA annotations for Order
│   │   └── ProductEntity.java                # Persistence-specific entity with JPA annotations for Product
│   └── mapper
│       ├── CustomerMapper.java               # Mapper to convert between domain and persistence entities for Customer
│       ├── OrderMapper.java                  # Mapper to convert between domain and persistence entities for Order
│       └── ProductMapper.java                # Mapper to convert between domain and persistence entities for Product
│
├── config
│   ├── exception
│   │   ├── CustomExceptionHandler.java       # Global exception handler
│   │   └── ErrorResponse.java                # Error response object
│   ├── security
│   │   └── SecurityConfig.java               # Security configurations
│   │   └── OpenApiConfig.java                # Swagger/OpenAPI configuration
│   ├── UseCaseConfiguration.java             # Bean configuration for use cases
│   └── RepositoryConfiguration.java          # Bean configuration for repositories
│
├── APIApplication.java                       # Main entry point for Spring Boot


In conclusion, keeping repository interfaces in the domain layer is common practice in Clean Architecture as long as only the interface is there, not the actual persistence logic. The persistence logic belongs in the infrastructure layer.








