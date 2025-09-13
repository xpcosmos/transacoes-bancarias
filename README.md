# Transações Bancárias - Spring Boot

Uma API RESTful em Java/Spring Boot para simular operações de transferência de dinheiro entre usuários e lojistas.

## 📋 Índice

- [Transações Bancárias - Spring Boot](#transações-bancárias---spring-boot)
  - [📋 Índice](#-índice)
  - [🛠 Tecnologias e Ferramentas](#-tecnologias-e-ferramentas)
  - [Instalação e Execução](#instalação-e-execução)
    - [Clonagem de repositório](#clonagem-de-repositório)
    - [Configurando ambiente](#configurando-ambiente)
      - [Exemplo de `.env`](#exemplo-de-env)
    - [Inicialização](#inicialização)
      - [Build de projeto e instalação de packages](#build-de-projeto-e-instalação-de-packages)
      - [Inicialização de contâiner Docker](#inicialização-de-contâiner-docker)
  - [Documentação](#documentação)
    - [Swagger/OpenAPI](#swaggeropenapi)
    - [Endpoints Principais](#endpoints-principais)
      - [1. Cadastrar Usuário](#1-cadastrar-usuário)
      - [2. Cadastrar Usuários em Lote](#2-cadastrar-usuários-em-lote)
      - [3. Realizar Transferência](#3-realizar-transferência)
    - [Exemplo de Uso com cURL](#exemplo-de-uso-com-curl)
  - [📁 Estrutura do Projeto](#-estrutura-do-projeto)

## 🛠 Tecnologias e Ferramentas

- **Java**: 21+
- **Spring Boot**: 3.2.0+
- **Banco de Dados**: H2 (desenvolvimento) / PostgreSQL (produção)
- **Build Tool**: Maven
- **Containerização**: Docker + Docker Compose
- **Testes**: JUnit 5, Mockito
- **Documentação**: Springdoc OpenAPI

## Instalação e Execução

### Clonagem de repositório

```bash
git clone https://github.com/xpcosmos/transacoes-bancarias
cd transacoes-bancarias
```

### Configurando ambiente

Antes de iniciar, deve-se configurar as variáveis de ambiente.

#### Exemplo de `.env`

```bash
POSTGRES_PASSWORD=123
POSTGRES_HOST=localhost
POSTGRES_USER="dev"
POSTGRES_DB="test_db"
POSTGRES_PORT=5432
```

### Inicialização

Uma vez que as variáveis de ambiente estejam configuradas, será possível executar a aplicação.

#### Build de projeto e instalação de packages

```bash
mvn clean package
```

#### Inicialização de contâiner Docker

```bash
docker compose up
# A aplicação estará disponível em: http://<HOST>:8080
```

## Documentação

### Swagger/OpenAPI

A documentação interativa está disponível em:

- **Swagger UI**: <http://localhost:8080/swagger-ui.html>
- **OpenAPI**: <http://localhost:8080/v3/api-docs>

### Endpoints Principais

#### 1. Cadastrar Usuário

```http
POST /user
Content-Type: application/json
{

    "nomeCompleto":"Joao Lucas",
    "documentoId":"1",
    "email":"email1",
    "tipoUsuario":"LOJISTA" | "COMUM",
    "saldo":1000,
    "senha":"123"
},
```

#### 2. Cadastrar Usuários em Lote

É possível fazer cadastro de usuários em Lote:

```http
POST /user/batch
Content-Type: application/json
[
  {

    "nomeCompleto":"Joao Lucas",
    ... # Mesmos parametros para cadastrar um único usuário
  },
  # Outros usuários
  {...},
  {...}
],
```

#### 3. Realizar Transferência

```http
POST /api/transfers
Content-Type: application/json

{
  "value": 100.50,
  "payerId": 1,
  "payeeId": 2
}
```

### Exemplo de Uso com cURL

```bash
# Cadastrar usuário comum
curl -X POST http://localhost:8080/user/batch \
  -H "Content-Type: application/json" \
  -d '
[
  {
    "nomeCompleto":"Joao Lucas",
    "documentoId":"1",
    "email":"email1",
    "tipoUsuario":"LOJISTA",
    "saldo":1000,
    "senha":"123"
  },
  {
    "nomeCompleto":"Renato Santos",
    "documentoId":"2",
    "email":"email2",
    "tipoUsuario":"COMUM",
    "saldo":1000,
    "senha":"123"
  },
  {
    "nomeCompleto":"Gabriela Alencar",
    "documentoId":"3",
    "email":"email3",
    "tipoUsuario":"LOJISTA",
    "saldo":1000,
    "senha":"123"
  },
  {
    "nomeCompleto":"Ismael Fonsseca",
    "documentoId":"4",
    "email":"email4",
    "tipoUsuario":"COMUM",
    "saldo":1000,
    "senha":"123"
  }
]'

# Realizar transferência
curl -X POST http://localhost:8080/transfer \
  -H "Content-Type: application/json" \
  -d '{
    "payee":2,
    "payer":4,
    "value":200
}'
```

## 📁 Estrutura do Projeto

```directory
src/
├── main/
│   ├── java/com/xpcosmos/transacoes_bancarias/
│   │   ├── controllers/            # Controladores REST
│   │   ├── dto/                    # Data Transfer Objects
│   │   ├── infra/                  # Configurações de infra
│   │   ├── exception/              # Exceções customizadas
│   │   ├── model/                  # Entidades JPA
│   │   ├── repository/             # Interfaces Spring Data JPA
│   │   ├── validation/             # Validações customizadas
│   │   └──service/                 # Lógica de negócio
│   │      └── external/            # Serviços externos
│   └── resources/
│       ├── application.yaml  # Configurações
└── test/
    └── java/com/picpay/simplificado/
        ├── assets/                 # Recursos compartilhados para testes
        ├── controllers/            # Testes unitários de controllers
        ├── respositories/          # Testes unitários de repositorios
        └── services/               # Testes unitários de servicoes
```
