# Teste
Este é um repositório para um desafio de programação. O objetivo deste desafio é desenvolver uma aplicação que permita o consumo de uma fila rabbitmq sendo possível salvar os dados num banco de dados e recuperação das informações para emissão de relatório.

# Funcionalidades
- Processamento de um pedido;
- Consulta de pedidos por cliente;
- Consulta valor total do pedido;
- Consulta número de pedidos por cliente;

# Usando um task list, marcando tarefa como completada ou não  

- [X] Criação da estrutura do projeto
- [X] Criação dos endpoints
- [X] Criação da configuração do RabbitMQ
- [X] Consumindo a mensagem de order da Queue
- [X] Criação do arquivo dockerfile
- [X] Criação do docker-compose do projeto
- [X] Testes unitários
- [ ] Testes de integração com o MongoDB
- [ ] Testes de integração com o RabbitMQ
- [X] Refactoring

# Tecnologias utilizadas
- Java
- Spring
- MongoDB
- Docker
- Maven

# Como executar o projeto
Para executar o projeto, é necessário ter o Docker Desktop

### 1° Step - Clone o projeto: https://github.com/Mikael-GIT/desafio.git

### 2° Step - Vá até o diretório docker por meio do comando: cd docker

### 3° Step - Execute o comando docker-compose up

### 4° Step - Verifique se os containers estão up por meio do comando docker ps

O processo vai levar alguns minutos e 3 containers serão iniciados:
- Mongodb
- RabbitMQ
- Container da aplicação

# Endpoints disponíveis
- GET /orders/{id}: Consulta o valor total de um pedido pelo id dele
- GET /orders/clients/{id}: Consulta total de pedidos realizados por um cliente pelo seu id

- GET /orders/clients/{id}/orders: Consulta pedidos realizados por um cliente

# RabbitMQ
Abra no navegador: http://localhost:15672/

E faça login com as credenciais
username: guest
password: guest

Acesse o mongoDB:
localhost:27017
