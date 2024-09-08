# EZ-Fast-Food

## Descrição

O EZ-Fast-Food é um sistema desenvolvido para solucionar os desafios enfrentados por uma lanchonete que está se expandindo devido ao seu sucesso.

## Entregáveis

### Cliente
- **Cadastro do Cliente** 
- **Identificação do Cliente via CPF** 
  
### Produto
- **Criar, Editar e Remover Produtos**
  - **Criar Produto:** 
  - **Editar Produto:** 
  - **Remover Produto:**
- **Buscar Produtos por Categoria** 

### Pedido
- **Fake Checkout**
  - Envio dos produtos escolhidos para a fila (checkout é a finalização do pedido). 
- **Listar todos os Pedidos** 

## Entregas Extras
Esses endpoints foram implementados para facilitar as validações. 
### Cliente
- **Filtrar Cliente por CPF** 

### Produto
- **Listar Todos os Produtos** 

### Categoria
- **Listar Todas as Categorias** 

### Pedidos
- **Listar Todos os Pedidos Não Finalizados** 

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.1**
- **Hibernate**
- **PostgreSQL**
- **Docker e Docker Compose**
- **OpenApi**

## Modelagem do Banco de Dados
![modelagem](https://github.com/user-attachments/assets/bfa15302-2957-47cc-afdf-85cea99b5b7a)
## Arquitetura

### Arquitetura Hexagonal

A arquitetura hexagonal foi a proposta pela FIAP, ela foi escolhida para isolar a lógica de negócios dos detalhes de infraestrutura, promovendo uma separação clara de responsabilidades. Essa abordagem facilita a manutenção e evolução da aplicação, permitindo a introdução de novas tecnologias e adaptações sem impacto significativo nas funcionalidades principais.

### Estrutura de Diretórios

- `src/main/java/br/com/fiap/ez/fastfood`
  - `adapters/in`
  - `adapters/out`
  - `application`
  - `config`
  - `domain`
- `src/main/resources`
- `docker`

## Instruções de Configuração e Execução

### Pré-requisitos

- Docker instalado.

### Clonar o Repositório

```sh
git clone https://github.com/tchfer/ez-fast-food.git
cd ez-fast-food
```

### Execução com Docker Compose
Para iniciar a aplicação e o banco de dados PostgreSQL, execute o seguinte comando na raiz desse projeto:

```sh
docker-compose up --build
```

Isso irá:

1. Baixar as imagens necessárias, caso ainda não estejam disponíveis.
2. Construir as imagens da aplicação e do banco de dados.
3. Iniciar os contêineres, incluindo as duas intâncias necessárias, a aplicação Spring Boot e o banco de dados PostgreSQL.

## Acessando a aplicação
A aplicação estará disponível em http://localhost:8080

## Documentação de APIs
As APIs foram documentadas utilizando OpenAPI e estão disponíveis em http://localhost:8080/swagger-ui/index.html<br>

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
@W4GN3R: RM357088


