# Sistema de Empréstimo de Equipamentos — Backend

API REST desenvolvida com **Java e Spring Boot** para gerenciar equipamentos, colaboradores e empréstimos, permitindo controlar a disponibilidade dos equipamentos e acompanhar suas devoluções.

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Data JPA
- Spring Web MVC
- Spring Validation
- Maven
- PostgreSQL
- H2 Database

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de equipamentos.
- Cadastro e consulta de colaboradores.
- Registro de empréstimos.
- Registro de devoluções.
- Controle de disponibilidade dos equipamentos.
- Consulta de empréstimos por colaborador.
- Identificação de empréstimos em atraso.
- Persistência de dados com PostgreSQL em produção.

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/equipamentos` | Lista equipamentos |
| POST | `/equipamentos` | Cadastra equipamento |
| PUT | `/equipamentos/{id}` | Atualiza equipamento |
| DELETE | `/equipamentos/{id}` | Remove equipamento |
| GET | `/colaboradores` | Lista colaboradores |
| POST | `/colaboradores` | Cadastra colaborador |
| GET | `/colaboradores/{id}/emprestimos` | Consulta empréstimos do colaborador |
| GET | `/emprestimos` | Lista empréstimos |
| POST | `/emprestimos` | Registra empréstimo |
| PUT | `/emprestimos/{id}/devolucao` | Registra devolução |

## Executar localmente

**Pré-requisitos:**
- Java 25 ou compatível com a configuração do projeto.
- Maven.

1. Clone o repositório:

```bash
git clone https://github.com/dudavalmor/sistema-emprestimo-equipamentos.git
```

2. Entre na pasta:

```bash
cd sistema-emprestimo-equipamentos
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

`http://localhost:8080`

## Banco de dados

- Desenvolvimento: H2 Database.
- Produção: PostgreSQL hospedado no Render.
- Configuração de produção realizada por meio de variáveis de ambiente.

## Deploy

API publicada no Render:

https://sistema-emprestimo-equipamentos-xqqy.onrender.com

## Autora

Desenvolvido por **Eduarda** como projeto de estudo e prática de desenvolvimento backend com Java e Spring Boot.
