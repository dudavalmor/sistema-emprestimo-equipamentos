# Sistema de Empréstimo de Equipamentos

API REST desenvolvida com Java e Spring Boot para gerenciar o empréstimo e a devolução de equipamentos de uma empresa.

O sistema permite cadastrar equipamentos e colaboradores, registrar empréstimos, controlar devoluções e identificar empréstimos em atraso, aplicando regras de negócio para garantir a integridade das operações.

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.1.2
- Spring Data JPA
- Hibernate
- Banco de dados H2
- Maven
- JUnit 5
- Jakarta Validation

## Funcionalidades

### Equipamentos
- Cadastrar equipamentos.
- Listar todos os equipamentos.
- Buscar equipamento por ID.
- Atualizar informações de equipamentos.
- Excluir equipamentos sem histórico de empréstimos.
- Filtrar equipamentos por tipo e disponibilidade.

### Colaboradores
- Cadastrar colaboradores.
- Listar colaboradores.
- Buscar colaborador por ID.
- Consultar empréstimos de um colaborador.

### Empréstimos
- Registrar empréstimos de equipamentos.
- Consultar empréstimos.
- Buscar empréstimo por ID.
- Registrar devoluções.
- Consultar empréstimos em atraso.

## Regras de negócio

O sistema implementa as seguintes regras:

1. Não permite cadastrar equipamentos com patrimônio duplicado.
2. Todo equipamento novo é cadastrado como disponível.
3. Não permite cadastrar colaboradores com e-mail duplicado.
4. Somente equipamentos disponíveis podem ser emprestados.
5. O prazo de devolução é de 14 dias após a data do empréstimo.
6. Não permite devolver um empréstimo mais de uma vez.
7. Ao registrar uma devolução, o equipamento volta a ficar disponível.
8. Identifica empréstimos não devolvidos cuja data prevista já passou.
9. Não permite excluir equipamentos que possuem histórico de empréstimos.
10. Não permite que colaboradores com empréstimos atrasados realizem novos empréstimos.

## Endpoints principais

### Equipamentos — `/equipamentos`

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/equipamentos` | Cadastrar equipamento |
| GET | `/equipamentos` | Listar equipamentos |
| GET | `/equipamentos/{id}` | Buscar por ID |
| PUT | `/equipamentos/{id}` | Atualizar equipamento |
| DELETE | `/equipamentos/{id}` | Excluir equipamento |

Filtros disponíveis:
- `tipo`
- `disponivel`

Exemplo:

`GET /equipamentos?tipo=NOTEBOOK&disponivel=true`

### Colaboradores — `/colaboradores`

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/colaboradores` | Cadastrar colaborador |
| GET | `/colaboradores` | Listar colaboradores |
| GET | `/colaboradores/{id}` | Buscar por ID |
| GET | `/colaboradores/{id}/emprestimos` | Consultar empréstimos do colaborador |

### Empréstimos — `/emprestimos`

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/emprestimos` | Registrar empréstimo |
| GET | `/emprestimos` | Listar empréstimos |
| GET | `/emprestimos/{id}` | Buscar por ID |
| PUT | `/emprestimos/{id}/devolucao` | Registrar devolução |

Filtro disponível:
- `atrasados`

Exemplo:

`GET /emprestimos?atrasados=true`

## Como executar o projeto

### Pré-requisitos

- Java 25 instalado.
- Maven instalado ou utilização do Maven Wrapper.
- IDE de sua preferência, como IntelliJ IDEA.

### Executando localmente

1. Clone o repositório:

```bash
git clone URL_DO_REPOSITORIO
```

2. Entre na pasta do projeto:

```bash
cd emprestimo
```

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

4. Acesse a API:

`http://localhost:8080`

## Banco de dados

O projeto utiliza o banco de dados H2, configurado em modo arquivo para persistência local.

O Hibernate está configurado com `ddl-auto=update`, permitindo atualizar a estrutura das tabelas conforme as entidades.

O console H2 está habilitado para consultas e inspeção do banco durante o desenvolvimento.

## Testes

Foram implementados testes automatizados utilizando JUnit 5.

- `EquipamentoTest`: 3 testes.
- `EmprestimoTest`: 7 testes.

**Total: 10 testes automatizados aprovados.**

Para executar os testes:

```bash
./mvnw test
```

## Tratamento de erros

A API utiliza exceções personalizadas e um tratamento global de erros com `@RestControllerAdvice`.

Principais respostas HTTP:

- `200 OK`: operação realizada com sucesso.
- `201 Created`: recurso cadastrado.
- `400 Bad Request`: dados inválidos.
- `404 Not Found`: recurso não encontrado.
- `409 Conflict`: conflito com uma regra de negócio.

## Autora
Eduarda Valmor

Desenvolvido como projeto de estudo, com foco em lógica de programação, desenvolvimento de APIs REST, Java e Spring Boot.