# Aula 2 — JPA: Validações e Constraints (19/08/2026)

## Projeto
Spring Boot com JPA — `exemplojpa`

---

## Tópicos abordados

### 1. Mapeamento de entidade com JPA
- `@Entity` — marca a classe como tabela
- `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` — chave primária auto-incrementada
- `@Column(nullable = false, length = 1000)` — configura DDL da coluna (not null, tamanho)

### 2. CHECK constraint no banco de dados

`@Column` **não** suporta min/max — apenas configura DDL (nome, nullable, length, etc.).

Para criar uma CHECK constraint no banco, há duas abordagens:

#### Hibernate (`@Check`) — nível de classe
```java
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "valor_hora_aula >= 10.0 AND valor_hora_aula <= 500.0")
public class Professor { ... }
```

#### JPA 3.2 (`@CheckConstraint`) — via `@Column` ou `@Table`
`@CheckConstraint` tem `@Target({})` — **não pode ser usada diretamente** num campo. Deve ser passada como atributo de outra anotação:

```java
// via @Column (campo)
@Column(check = @CheckConstraint(constraint = "valor_hora_aula >= 10.0 AND valor_hora_aula <= 500.0"))
private Double valorHoraAula;

// via @Table (entidade)
@Table(check = @CheckConstraint(
    name = "chk_valor_hora_aula",
    constraint = "valor_hora_aula >= 10.0 AND valor_hora_aula <= 500.0"
))
```

| | `@Check` / `@CheckConstraint` |
|---|---|
| Onde valida | Banco de dados (DDL) |
| Requer `@Valid` | Não |
| Visível no schema | Sim |

---

### 3. Repository com EntityManager

```java
@Repository
public class ProfessorRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Professor save(Professor professor) {
        return entityManager.merge(professor);
    }

    public List<Professor> getAll() {
        return entityManager
                .createQuery("from Professor", Professor.class)
                .getResultList();
    }
}
```

- `entityManager.merge()` — insere ou atualiza (upsert)
- `createQuery("from Professor", ...)` — JPQL (usa nome da classe, não da tabela)
- `@Transactional` — obrigatório em operações de escrita
