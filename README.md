# ProtCar - API

API REST responsável por receber e armazenar as solicitações de cotação de proteção veicular geradas pela landing page, além de autenticar o acesso da equipe de vendas ao painel administrativo.

## Stack

| Camada | Tecnologias |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot (Web, Data JPA, Validation, Security/JWT) |
| Auxiliares | Lombok, MapStruct |
| Banco de dados | PostgreSQL (Docker em desenvolvimento, RDS em produção) |
| Infraestrutura | AWS Elastic Beanstalk + CloudFront |

## Estrutura

```
back-end/
└── src/main/java/com/protcar/api/
    ├── controller/     endpoints de cotações e login
    ├── dto/             records de request/response
    ├── mapper/          conversores MapStruct
    ├── model/           entidades JPA
    ├── repository/      Spring Data JPA
    └── service/         regras de negócio e validações
```

## Como rodar localmente

### Pré-requisitos
- Java 21+
- Docker e Docker Compose

### Passos

Suba o banco de dados:

```bash
docker compose up -d
```

Confirme as credenciais em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/protcar_db
spring.datasource.username=admin
spring.datasource.password=adminpassword
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

A API sobe por padrão em `http://localhost:5000`.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/cotacoes` | Registra um novo lead |
| `GET` | `/api/cotacoes` | Lista os leads salvos (requer token JWT) |
| `POST` | `/api/auth/login` | Autentica o acesso ao painel e retorna o token |

## Deploy

- **Banco de dados:** RDS PostgreSQL.
- **API:** pacote `.jar` implantado no Elastic Beanstalk, com CloudFront dedicado para habilitar HTTPS.

## Licença

Desenvolvido sob medida para a ProtCar - Associação de Benefícios. Todos os direitos reservados.
