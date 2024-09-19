# ez-fast-food-clean-arch
Tech Challenge 2 - Clean Architecture e Kubernetes

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



Colinha:
1. Core Domain (Entities)
	Customer.class
2. Domain Repositories (Abstractions)
	CustomerRepository.java - interface
3. Use Cases (Application Business Rules)
	CustomerUseCase.java
4. DTOs (Data Transfer Objects)
	CustomerDTO.java
5. Repository (Persistence) Layer
	CustomerRepositoryImpl.java
6. JPA Entities
	CustomerEntity.java
7. Mappers to Convert Between Domain and Entity
	CustomerMapper.java
8. Controllers (Interface Adapters)
	CustomerController.java
9. Application (Infrastructure Layer)
	RepositoryConfiguration.java
	UseCaseConfiguration.java






