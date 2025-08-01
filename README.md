# Estudo de Spring Data JPA

Projeto feito para estudo do Spring Data JPA da [vídeo-aula](https://www.youtube.com/watch?v=Ca30sv9EbLo) da Michelli Brito.

## Tecnologias utilizadas

| Tecnologia  | Versão |
|-------------|--------|
| Java        |     17 |
| Spring Boot |  3.5.4 |
| PostgreSQL  |     13 |

## Variáveis de ambiente

As variáveis de ambiente utilizadas neste projeto:

| Variável          | Tipo   |
|-------------------|--------|
| POSTGRES_USER     | string |
| POSTGRES_PASSWORD | string |
| POSTGRES_DB       | string |

## Conclusões

### Criação de tabelas automáticas

Criação de tabelas através da ORM e a utilização da propriedade `spring.jpa.hibernate.ddl-auto=true` no arquivo `application.properties`.

Serialização do modelo para formato JSON de forma que as relações não sobrecarreguem a busca das entidades.

### Relações entre entidades

Conceitos de relacionamento de entidades para 1-1(Um pra Um), 1-N(Um pra Muitos) e N-N(Muitos pra Muitos), assim como definição das relações no código com `@ManyToOne`, `@OneToMany`, `@OneToOne` e `@ManyToMany`. O uso do `@JoinColumn` para criação da coluna que irá enlaçar a chave estrangeira com a entidade.

As propriedades principais de cada _decorator_ para estabelecer a relação entre as entidades.

