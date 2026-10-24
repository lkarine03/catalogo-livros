# Catálogo de Livros

Projeto simples de estudos desenvolvido para praticar **Java, Spring Boot, API REST e operações CRUD**.

A aplicação permite cadastrar, listar, consultar, atualizar e excluir livros de um catálogo. Cada livro possui título, autor e ano de publicação.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- H2 Database (banco em memória)
- Maven

## Rotas

| Método | Rota | Função |
|---|---|---|
| GET | `/livros` | Lista os livros |
| GET | `/livros/{id}` | Busca um livro |
| POST | `/livros` | Cadastra um livro |
| PUT | `/livros/{id}` | Atualiza um livro |
| DELETE | `/livros/{id}` | Exclui um livro |

Exemplo de JSON para cadastrar um livro:

```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "anoPublicacao": 1899
}
```

## Como executar localmente

Com Java 21 e Maven instalados, na pasta do projeto:

```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080/livros`.

**Observação:** o H2 está configurado em memória. Os dados não persistem após encerrar a aplicação. Este é um projeto para aprendizado, não um sistema de produção.

O código ainda precisa ser executado e testado localmente.
