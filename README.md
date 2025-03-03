# Mini SPC

**Mini SPC** é um sistema de gerenciamento de clientes, empresas e dívidas, desenvolvido com **Spring Boot** e **PostgreSQL**. O sistema adota boas práticas de desenvolvimento, utilizando ferramentas modernas e seguindo os princípios SOLID para garantir escalabilidade e manutenção eficiente. 

## Tecnologias

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Spring Boot** - Framework para o desenvolvimento de aplicações Java.
- **PostgreSQL** - Banco de dados relacional utilizado para armazenar as informações.
- **JPA** (Java Persistence API) - Para mapear as entidades do banco de dados.
- **Lombok** - Biblioteca que reduz o código boilerplate no desenvolvimento.
- **Swagger** - Para documentação automática da API.
- **Collections** - Para manipulação eficiente de dados.
- **DTO (Data Transfer Objects)** - Para transferência de dados entre camadas.
- **MVC (Model-View-Controller)** - Padrão de arquitetura para separação de responsabilidades.
- **SOLID** - Princípios de design de software aplicados para garantir um código limpo e manutenível.

## Funcionalidades

O sistema **Mini SPC** oferece as seguintes funcionalidades principais:

- **Gerenciamento de Clientes**: Cadastro, atualização, exclusão e consulta de clientes.
- **Gerenciamento de Empresas**: Cadastro, atualização, exclusão e consulta de empresas.
- **Gerenciamento de Dívidas**: Cadastro, atualização, exclusão e consulta de dívidas associadas a clientes e empresas.
- **Paginação e Busca**: Implementação de paginação nas listagens de clientes e dívidas, além de filtros para facilitar a busca.
- **Autenticação e Autorização**: Controle de acesso a funcionalidades restritas, com base em roles de usuários.

## Instalação

Para rodar o projeto localmente, siga os seguintes passos:

### Pré-requisitos

- **JDK 17 ou superior**
- **Maven**
- **PostgreSQL**

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/mini-spc.git
   cd mini-spc
   ```

### Configure o banco de dados PostgreSQL:

Crie um banco de dados chamado `mini_spc` no PostgreSQL ou ajuste as configurações no arquivo `application.properties` para refletir as credenciais do seu banco de dados.

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mini_spc
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Executando a aplicação

Após configurar o banco de dados e garantir que todas as dependências estejam corretamente instaladas, siga os passos abaixo para executar a aplicação localmente.

#### Executando via Maven

1. Navegue até o diretório do projeto:

   ```bash
   cd /caminho/para/o/projeto
     ```

#### Documentação Swagger




