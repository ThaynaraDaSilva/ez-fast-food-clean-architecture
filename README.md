# EZ-Fast-Food - Clean Architecture e Kubernetes

## Contextualização

O **EZ-Fast-Food API** é uma solução desenvolvida para uma lanchonete em expansão, utilizando a arquitetura limpa (Clean Architecture) para assegurar uma separação clara entre a lógica de negócios e os detalhes de infraestrutura. A API é implantada com Docker Kubernetes.

## Desenho de arquitetura
![image](https://github.com/user-attachments/assets/0c53ef23-645c-4125-a7e5-12f00b48bc1b)

## Arquitetura limpa (Clean architecture)

A *clean architecture* foi adotada para garantir uma separação clara das responsabilidades da API, facilitando a manutenção e a evolução da solução ao isolar a lógica de negócios dos detalhes de implementação.

## Estrutura de diretórios do projeto
```
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   └── out
│       └── repository
├── application
│   ├── usecases
│   └── dto
├── domain
│   ├── model
│   └── repository
├── frameworks
│   ├── config
│   └── exception
├── infrastructure
│   ├── config
│   ├── mapper
│   └── persistence
├── APIApplication.java
k8s/                      # Arquivos de manifesto Kubernetes
postman-jmeter/            # Collection para testes no Postman e Apache JMeter
```

## Modelagem do banco de dados

![modelagem](https://github.com/user-attachments/assets/bfa15302-2957-47cc-afdf-85cea99b5b7a)
## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.1
- Hibernate
- PostgreSQL 12
- Docker
- Kubernetes
- OpenApi

## Entregáveis

### Cliente
- **Cadastro do cliente** (http://localhost:30000/customers/create-new)
- **Identificação do cliente via CPF** (http://localhost:30000/customers/find-by-cpf/{cpf})
- **Login** (http://localhost:30000/customers/customers/login)
  
### Produto
- **Criar Produto** (http://localhost:30000/products/create-new)
- **Editar Produto** (http://localhost:30000/products/update-by-id/{id})
- **Remover Produto** (http://localhost:30000/products/delete-by-id/{id})
  - regra 1: não é permitido remover um produto que está associado a pedido.
- **Buscar produtos por categoria** (http://localhost:30000/products/find-by-category-id/{id})

### Pedido
- **Fake checkout** (http://localhost:30000/orders/checkout): envio dos produtos escolhidos para a fila (checkout é a finalização do pedido).
- **Listar pedidos não finalizados**(http://localhost:30000/orders/list-uncompleted-orders)
  - regra 1: considerar somente pedidos com os status **READY** , **IN_PREPARATION** e **RECEIVED**.
  - regra 2: pedidos mais antigos devem aparecer primeiro.
  
### Pagamento (Webhook)
- **Atualizar status do pagamento** (http://localhost:30000/payments/webhook/status):
  - regra: somente permitido atualizar o status para **OK** ou **CANCELLED** se o pagamento estiver com o status: **PENDING**

## Entregas Extras
Esses endpoints foram implementados para facilitar as validações. 
### Cliente
- **Filtrar cliente por CPF** (http://localhost:30000/customers/find-by-cpf/{cpf})
- **Listar todos os clientes** (http://localhost:30000/customers/list-all) 


### Produto
- **Listar todos os produtos** (http://localhost:30000/products/list-all)

### Pedidos
- **Listar todos os pedidos** (http://localhost:30000/orders/list-all)

### Pagamento
- **Verificar status do pagamento** (http://localhost:30000/payments/check-status?paymentId={id})

## Instruções de configuração e execução

### Pré-requisitos

- Docker Desktop instalado.
- Kubernetes habilitado no Docker Desktop.

**Instruções para habilitar Kubernetes no Docker Desktop:**
```sh
1. Abra o Docker Desktop.
2. Vá para as Configurações.
3. Selecione a aba Kubernetes.
4. Marque a opção Enable Kubernetes.
5. Clique em Apply & Restart para aplicar as mudanças.
```
### Clonar o repositório

```sh
git clone https://github.com/ThaynaraDaSilva/ez-fast-food-clean-architecture.git
cd ez-fast-food-clean-architecture
```

### Execução com kubernetes
Para iniciar o container da API e do banco de dados PostgreSQL, execute o seguinte comando na raiz desse projeto:

```sh
# o comando cria o namespace e aplica manifestos k8s presentes no diretório
kubectl apply -f k8s/namespace.yaml && kubectl apply -f k8s/
# ou execute dessa forma:
## 1.
kubectl apply -f k8s/namespace.yaml
## 2.
kubectl apply -f k8s/
```

## Documentação de APIs
```sh
1. A documentação das APIs está disponível no Swagger e pode ser acessada em http://localhost:30000/swagger-ui/index.html.
2. Foi disponibilizado uma **collection do postman**, disponível no diretório postman-jmeter/ deste repositório.
```
## Validação da API com Postman

### 1. Baixar a Collection:
```sh
1.1. Navegue até o diretório e baixe o arquivo .json correspondente à collection de endpoints.
```

### 2. Importar a collection no postman:
```sh
2.1. Inicie o postman.
2.2. No canto superior esquerdo, clique em *Import*.
2.3. Arraste e solte o arquivo .json ou selecione-o manualmente para importar a collection.
```

### 3. Selecionar o Ambiente:
```sh
3.1. Certifique-se de que o *environment* **ez-local** está configurado e selecionado no postman para garantir que as variáveis de ambiente (como a URL base, http://localhost:30000/api/) sejam utilizadas corretamente.

3.2. Caso o *environment* ez-local não esteja importado, baixe o arquivo de ambiente localizado no diretório postman-jmeter/, importe-o da mesma forma que fez com a *collection* e selecione-o no canto superior direito da interface do postman.
```
### Collection 1 - Jornada do fluxo de pedido

- A collection criada para o ez-fast-food contém todos os endpoints documentados no Swagger. Dentro dela, há um conjunto específico chamado Jornada - ez-fast-food, que deve ser usado para seguir o fluxo de pedido na sequência correta. Esse fluxo inclui os seguintes passos:
```sh
1. Listar Todos os Clientes: Execute o endpoint List all customers para visualizar os clientes cadastrados.

2. Cadastrar Novo Cliente: Use Create a new customer para criar um cliente.

3. Listar Todos os Produtos: Execute List all products para visualizar os produtos disponíveis e ver quais os IDs dos produtos para fazer um pedido.

4. Criar um Pedido (Fake Checkout): No endpoint Register a new order, selecione um produto pelo product_id e crie o pedido.

5. Registrar Pagamento: No endpoint Register a new payment, passe o order_id gerado anteriormente para realizar o pagamento.

6. Verificar Status do Pagamento: Execute Check payment status, passando o paymentId para verificar o status.

7. Listar Pedidos por Status: Execute List orders with status para ver pedidos que estão na fila de de preparação.

8. Atualizar Status do Pedido: Execute Update order status, alterando o status do pedido para RECEIVED, IN_PREPARATION, READY, ou COMPLETED.

9. Listar Todos os Pedidos: Com o endpoint List all orders, visualize todos os pedidos feitos.

Siga essa ordem na collection Jornada - ez-fast-food para simular o fluxo completo de um pedido.
```
##  Apache JMeter
Essa ferramenta foi utilizada para estressar a API durante nossas validações de HPA.

- Link para download: https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.6.3.zip
- Arquivo "ez-fast-food-api.jmx" disponível no diretório: postman-jmeter/ deste repositório.

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
