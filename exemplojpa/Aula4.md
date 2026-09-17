# Aula 4 — Spring Data JPA: Repositories e Consultas (02/09/2026)

## Projeto
Spring Boot com JPA — `exemplojpa`

---

## Tópicos abordados

### 1. Migração para `JpaRepository`

O `ProfessorRepository`, que antes era uma classe com `EntityManager`, passou a ser uma interface que estende `JpaRepository`:

```java
public interface ProfessorRepository
        extends JpaRepository<Professor, Long> {
}
```

Os tipos informados são:

- `Professor` — entidade gerenciada pelo repositório
- `Long` — tipo do identificador da entidade

Ao herdar de `JpaRepository`, o repositório passa a oferecer operações prontas, como:

- `save()` — insere ou atualiza uma entidade
- `findAll()` — busca todas as entidades
- `findById()` — busca uma entidade pelo ID
- `delete()` — remove uma entidade

Com isso, não é necessário implementar manualmente o `EntityManager`, as transações e as consultas básicas.

---

### 2. Query methods

O Spring Data JPA consegue criar consultas a partir do nome dos métodos declarados na interface:

```java
public List<Professor> findByNomeLike(String nome);

public List<Professor> findByValorHoraAulaGreaterThan(Double valor);
```

#### `findByNomeLike`

Busca professores cujo nome corresponde ao padrão informado. O caractere `%` funciona como curinga do SQL:

```java
professorRepository.findByNomeLike("Rafael%");
```

Essa consulta retorna nomes que começam com `Rafael`.

#### `findByValorHoraAulaGreaterThan`

Busca professores cujo valor da hora-aula é maior que o valor recebido:

```java
professorRepository.findByValorHoraAulaGreaterThan(95.0);
```

O trecho `GreaterThan` do nome do método é interpretado pelo Spring Data como uma comparação `>`.

> **Regra:** o nome do método deve seguir a convenção do Spring Data JPA e usar os nomes das propriedades da entidade.

---

### 3. Consultas JPQL com `@Query`

Quando o nome da consulta ficaria muito grande ou a consulta exige uma combinação específica de condições, é possível escrever a JPQL diretamente com `@Query`:

```java
@Query("SELECT p FROM Professor p WHERE p.titulacao = ?1 "
        + "AND p.valorHoraAula > ?2")
public List<Professor> findByTituloEValorHora(String titulacao,
        Double valor);
```

Essa consulta busca professores que:

1. Possuem a titulação informada no primeiro parâmetro (`?1`)
2. Possuem valor de hora-aula maior que o segundo parâmetro (`?2`)

A JPQL usa o nome da entidade (`Professor`) e os nomes dos atributos Java (`titulacao` e `valorHoraAula`), e não os nomes físicos das tabelas e colunas do banco.

O método equivalente seria chamado assim:

```java
professorRepository.findByTituloEValorHora("Doutor", 95.0);
```

---

### 4. Execução das consultas no `CommandLineRunner`

O método `init` da classe principal continua sendo executado na inicialização da aplicação por meio de um `CommandLineRunner`.

Nesta aula, depois de salvar o departamento, as disciplinas e os professores, foram adicionadas consultas para demonstrar o repositório:

```java
List<Professor> professores = professorRepository.findAll();
professores.forEach(System.out::println);

professores = professorRepository.findByValorHoraAulaGreaterThan(95.0);
professores.forEach(System.out::println);

professores = professorRepository.findByNomeLike("Rafael%");
professores.forEach(System.out::println);
```

O fluxo executado é:

1. Buscar e imprimir todos os professores
2. Buscar e imprimir professores com valor de hora-aula maior que `95.0`
3. Buscar e imprimir professores cujo nome começa com `Rafael`

---

### 5. Visualização do SQL gerado

Foram adicionadas propriedades no `application.properties` para acompanhar as consultas produzidas pelo Hibernate:

```properties
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
```

- `hibernate.show_sql=true` — exibe no console o SQL gerado pelo Hibernate
- `hibernate.format_sql=true` — organiza o SQL em um formato mais legível

Esse recurso permite relacionar cada método do repositório com a consulta SQL executada no banco H2.

---

### 6. Resumo dos conceitos de Spring Data JPA

| Recurso | Finalidade |
|---|---|
| `JpaRepository<Professor, Long>` | Fornece operações CRUD prontas para `Professor` |
| `findAll()` | Busca todos os registros |
| `findByNomeLike(...)` | Cria uma consulta a partir do nome do método |
| `findByValorHoraAulaGreaterThan(...)` | Filtra usando uma comparação maior que (`>`) |
| `@Query` | Permite definir uma consulta JPQL manualmente |
| `?1`, `?2` | Referenciam os parâmetros posicionais da consulta |
| `show_sql` e `format_sql` | Exibem e formatam o SQL gerado |

**Conceitos-chave:**

- **Repository Spring Data** — interface que encapsula o acesso a dados e reduz código repetitivo
- **Query method** — método cuja assinatura descreve automaticamente a consulta
- **JPQL** — linguagem de consulta orientada às entidades e seus atributos
- **Parâmetro posicional** — parâmetro identificado pela posição, como `?1` e `?2`

---

## Arquivos para commit

Os arquivos modificados nesta aula são:

- `src/main/java/com/example/exemplojpa/ExemplojpaApplication.java`
  - adiciona a execução das consultas no `CommandLineRunner`
- `src/main/java/com/example/exemplojpa/repositories/ProfessorRepository.java`
  - troca o repositório manual por `JpaRepository`
  - adiciona query methods e uma consulta com `@Query`
- `src/main/resources/application.properties`
  - habilita a exibição e a formatação do SQL do Hibernate
- `Aula4.md`
  - registra o conteúdo e as alterações da aula