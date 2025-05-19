# Sistema de Controle de Salas

## Visão Geral
Sistema de gerenciamento de reservas de salas baseado em microserviços, implementando os princípios de Domain-Driven Design (DDD). O projeto permite gerenciar usuários, salas e reservas de forma independente, utilizando comunicação assíncrona entre os serviços.

## Arquitetura

O sistema é composto por três microserviços principais:

- **ms-usuario**: Gerenciamento de usuários
- **ms-sala**: Gerenciamento de salas
- **ms-reserva**: Gerenciamento de reservas

Cada microserviço possui:
- Banco de dados independente (MySQL)
- API REST própria
- Segregação em redes Docker isoladas

![Arquitetura do Sistema](https://github.com/user-attachments/assets/a3c86f5d-10d0-4687-9933-9e12ceb2f970)

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Comunicação**: RabbitMQ (Spring AMQP)
- **Persistência**: MySQL
- **Containerização**: Docker e Docker Compose
- **Frontend**: Aplicação web na porta 3000
- **Gerenciamento de BD**: Adminer
- **Nginx**

## Padrão de Comunicação

O sistema implementa comunicação assíncrona via RabbitMQ para validações entre serviços:

- Quando uma reserva é criada, o ms-reserva envia uma mensagem para validar se o usuário existe
- O ms-usuario responde com status de validação
- A reserva é criada apenas se o usuário for válido

**Tolerância a falhas**: Em caso de indisponibilidade do serviço de usuários, as mensagens ficam armazenadas no broker para processamento quando o serviço voltar a funcionar.

![Comunicação via Message Broker](/Images/image.png)

## Como Executar

### Requisitos
- Docker e Docker Compose instalados

### Passos
1. Clone o repositório
2. Na raiz do projeto, execute:
   ```bash
   docker-compose up --build
   ```
   Portas e Acessos
- Frontend: http://localhost:3000
- API Usuários: http://localhost:8080
- API Salas: http://localhost:8081
- API Reservas: http://localhost:8082
- Adminer (Gerenciamento BD): http://localhost:4040
- RabbitMQ Dashboard: http://localhost:15672 (admin/admin)
### Banco de Dados
Cada microserviço possui seu próprio banco de dados MySQL:

- db_usuario (porta 3307)
- db_sala (porta 3308)
- db_reserva (porta 3309)

```Todos acessíveis via Adminer com usuário "admin" e senha "123". ```
   
