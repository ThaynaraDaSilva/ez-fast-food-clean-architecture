# EZ-Fast-Food - Clean Architecture e Kubernetes

## Descrição

O **EZ-Fast-Food** é um sistema desenvolvido para uma lanchonete em expansão, que utiliza a arquitetura limpa (Clean Architecture) para garantir uma separação clara entre a lógica de negócios e os detalhes de infraestrutura, e é implantado usando Docker e Kubernetes.

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

### Pedidos
- **Listar Todos os Pedidos Não Finalizados** 

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.1**
- **Hibernate**
- **PostgreSQL**
- **Docker**
- **Kubernetes**
- **OpenApi**

## Modelagem do Banco de Dados

![modelagem](https://github.com/user-attachments/assets/bfa15302-2957-47cc-afdf-85cea99b5b7a)

## Arquitetura

### Arquitetura Limpa (Clean Architecture)

A Clean Architecture foi adotada para garantir uma separação clara das responsabilidades do sistema, facilitando a manutenção e a evolução do software ao isolar a lógica de negócios dos detalhes de implementação.

### Estrutura de Diretórios

```
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
├── config
│   └── exception
│   └── security
├── APIApplication.java
k8s/            # Arquivos de manifesto Kubernetes
postman/        # Collection para testes no Postman
```

## Instruções de Configuração e Execução

### Pré-requisitos

- Docker Desktop instalado.
- Kubernetes habilitado no Docker Desktop.

**Instruções para habilitar Kubernetes no Docker Desktop:**

1. Abra o Docker Desktop.
2. Vá para as Configurações.
3. Selecione a aba Kubernetes.
4. Marque a opção Enable Kubernetes.
5. Clique em Apply & Restart para aplicar as mudanças.

### Clonar o Repositório

```sh
git clone https://github.com/ThaynaraDaSilva/ez-fast-food-clean-architecture.git
cd ez-fast-food-clean-architecture

```

### Execução com Kubernetes
Para iniciar a aplicação e o banco de dados PostgreSQL, execute o seguinte comando na raiz desse projeto:

Crie o namespace para o projeto:

```sh
kubectl create namespace ezfastfood
```

Aplique os manifestos do Kubernetes:

```sh
kubectl apply -f k8s/
```

Isso irá:

1. Subir os contêineres da aplicação e do PostgreSQL no Kubernetes.
2. Garantir que a aplicação esteja rodando corretamente.

## Acessando a aplicação
A aplicação estará disponível em http://localhost:80

## Documentação de APIs
A documentação das APIs está disponível no Swagger e pode ser acessada em http://localhost:80/swagger-ui/index.html.

## Testes de API com Postman

### Baixar e Importar a Collection no Postman

1. **Baixar a Collection:**

- A collection de testes pode ser encontrada no diretório postman/ deste repositório.
- Navegue até o diretório e baixe o arquivo .json correspondente à collection de endpoints.

2. **Importar a Collection no Postman:**

- Abra o Postman.
- No canto superior esquerdo, clique em Import.
- Arraste e solte o arquivo .json ou selecione-o manualmente para importar a collection.

3. **Selecionar o Ambiente:**

- Certifique-se de que o ambiente ez-local está configurado e selecionado no Postman para garantir que as variáveis de ambiente (como a URL base) sejam utilizadas corretamente.
- Caso o ambiente ez-local não esteja importado, baixe o arquivo de ambiente localizado no diretório postman/, importe-o da mesma forma que fez com a collection e selecione-o no canto superior direito da interface do Postman.

### Executar os Testes

Depois de importar a collection e o ambiente, você pode executar os testes diretamente clicando nos endpoints dentro da collection ou usando a aba **Runner** do Postman para testar múltiplos endpoints de forma automatizada.

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
