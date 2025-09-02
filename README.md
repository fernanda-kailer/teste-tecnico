# CRUD de Usuários com Spring Boot

Este projeto é uma API REST para gerenciamento de usuários, desenvolvida com Spring Boot. Permite cadastro, busca (com paginação e filtro por nome), atualização e exclusão de usuários. Também inclui autenticação JWT e documentação automática via Swagger.

---

## Funcionalidades

- Cadastro de usuários
- Listagem paginada de usuários
- Busca por nome
- Autenticação via JWT
- Documentação da API com Swagger UI
- Segurança configurada para proteger endpoints
- Configuração para lidar com CORS e tratamento básico de erros

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security com JWT
- Spring Data JPA
- H2 Database (ou outro banco configurado)
- SpringDoc OpenAPI (Swagger)
- Maven

---

## Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
Configure o banco de dados no application.properties (se necessário).

Rode a aplicação:

bash

Run
Copy code
mvn spring-boot:run
Acesse a documentação da API no navegador:

Run
Copy code
http://localhost:8080/swagger-ui.html
Endpoints principais
POST /auth/login - Autenticação
POST /usuarios - Cadastro de usuário
GET /usuarios - Listagem paginada e busca por nome
GET /usuarios/email?email=... - Busca por email (se implementado)
PUT /usuarios/{id} - Atualizar usuário
DELETE /usuarios/{id} - Deletar usuário
