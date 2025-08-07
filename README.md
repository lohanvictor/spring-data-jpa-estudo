# Estudo de Spring Data JPA

Este projeto tem como objetivo o estudo das relações entre entidades no contexto Spring Data JPA, seguindo o roteiro da [vídeo-aula](https://www.youtube.com/watch?v=Ca30sv9EbLo) da youtuber/desenvolvedora [Michelli Brito](https://www.youtube.com/@MichelliBrito).


### Sumário

- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Variáveis de ambiente](#variáveis-de-ambiente)
- [Rotas](#rotas)
  - [Criação de um livro](#criação-de-um-livro)
  - [Deleção de um livro](#deleção-de-um-livro)
- [Conclusões](#conclusões)
  - [Criação de tabelas automáticas](#criação-de-tabelas-automáticas)
  - [Relações entre entidades](#relações-entre-entidades)
  - [Interação nas tabelas](#interação-nas-tabelas)
  - [Customizaçao da exceções](#customização-de-exceções)

## Tecnologias utilizadas

| Tecnologia  | Versão |
|-------------|-------:|
| Java        |     17 |
| Spring Boot |  3.5.4 |
| PostgreSQL  |     13 |
| FlywayDB    |  9.8.1 |

## Variáveis de ambiente

As variáveis de ambiente utilizadas neste projeto:

| Variável          | Tipo   |
|-------------------|--------|
| POSTGRES_USER     | string |
| POSTGRES_PASSWORD | string |
| POSTGRES_DB       | string |

## Rotas

Foi utilizado o Flyway para _migrations_ que inserem dados iniciais de autores e editoras nas tabelas.

### Criação de um livro

Para criar um livro, é necessário fazer uma requisição **POST** para a seguinte rota:

> POST /bookstore/books

O corpo da requisição é a seguinte:

| Chave         |       Valor |
|---------------|------------:|
| title         |      String |
| publisherId   |        UUID |
| authorsId     | Array{UUID} |
| reviewComment |      String |

Um exemplo de _body_ para a requisição:

````http request
POST /bookstore/books
````

````json
{
  "title": "Sherlock Holmes 1",
  "publisherId": "c2f8d42b-c0b0-47b4-9883-0297691e8095",
  "authorsId": ["e2331c34-5044-4c9e-b557-8124e74cc59d", "ed245bd9-e500-46b0-acd4-b387339a556a"],
  "reviewComment": "Livro top show bala"
}
````

### Deleção de um livro

Para a deleção de um livro, é necessário fazer uma requisição **DELETE** para a seguinte rota:

> DELETE /bookstore/books/:id

A variável na rota é a seguinte:

| Chave        |       Valor |
|--------------|------------:|
| id           |        UUID |

Um exemplo de rota para a requisição:
````http request
DELETE /bookstore/books/c2f8d42b-c0b0-47b4-9883-0297691e8095
````

## Conclusões

### Criação de tabelas automáticas

Criação de tabelas através da ORM e a utilização da propriedade `spring.jpa.hibernate.ddl-auto=true` no arquivo `application.properties`.

### Relações entre entidades

Conceitos de relacionamento de entidades para 1-1(Um pra Um), 1-N(Um pra Muitos) e N-N(Muitos pra Muitos), assim como definição das relações no código com `@ManyToOne`, `@OneToMany`, `@OneToOne` e `@ManyToMany`. O uso do `@JoinColumn` para criação da coluna que irá enlaçar a chave estrangeira com a entidade.

As propriedades principais de cada _decorator_ para estabelecer a relação entre as entidades.

### Interação nas tabelas

Serialização do modelo para formato JSON de forma que as relações não sobrecarreguem a busca das entidades com `@JsonProperty`, configurando a identificação da propriedade apenas para modo de escrita, aliada a utilização do `fetch = FetchType.LAZY`.

Utilização da anotação `@Transaction` para alterações múltiplas no banco de dados, garantindo segurança e _rollback_ em caso de falhas de alteração no banco de dados. No caso deste projeto, há uma função `BookService::save` que manipula vários dados no banco de dados e a função `BookService::delete` que deleta o livro e sua avaliação correspondente.

### Customização de exceções

Utilização do `@ControllerAdvice` para lidar com exceções customizadas lançadas pelos serviços. 

- **ConflictException**: exceção criada para erros de conflito (HttpStatus 409).
  - No caso do projeto, foi criado caso o livro seja criado com um título que já exista
- **NotFoundException**: exceção criada para erros de não encontrado (HttpStatus 404).
  - No caso do projeto, foi criado caso seja feita a deleção de um UUID de um livro que não existe.