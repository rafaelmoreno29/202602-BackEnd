# Aula 3 — JPA: Relacionamentos entre Entidades (26/08/2026)

## Projeto
Spring Boot com JPA — `exemplojpa`

---

## Tópicos abordados

### 1. Novas entidades criadas

Além de `Professor` (criada na Aula 2), foram adicionadas duas novas entidades:

- **`Departamento`** — representa um departamento acadêmico
- **`Disciplina`** — representa uma disciplina/matéria

---

### 2. Relacionamentos JPA

JPA permite mapear as associações entre tabelas diretamente nas classes Java, usando anotações. O Hibernate gera automaticamente as chaves estrangeiras e tabelas de junção no banco.

#### 2.1 `@ManyToOne` / `@OneToMany` — Professor e Departamento

Um professor pertence a **um** departamento. Um departamento tem **muitos** professores.

**Lado "muitos" (`Professor`) — dono do relacionamento:**
```java
@ManyToOne
@JoinColumn(name = "departamento_id")
private Departamento departamento;
```
- `@ManyToOne` — muitos professores para um departamento
- `@JoinColumn(name = "departamento_id")` — cria a coluna de chave estrangeira na tabela `Professor`

**Lado "um" (`Departamento`) — lado inverso:**
```java
@OneToMany(mappedBy = "departamento")
private List<Professor> professores;
```
- `mappedBy = "departamento"` — indica que o campo `departamento` em `Professor` é o dono do relacionamento; esta tabela **não** cria nova coluna

> **Regra:** `mappedBy` sempre fica no lado que **não** possui a chave estrangeira.

#### 2.2 `@ManyToMany` — Professor e Disciplina

Um professor pode ministrar **muitas** disciplinas. Uma disciplina pode ser ministrada por **muitos** professores. Isso exige uma **tabela de junção**.

**Lado dono (`Professor`):**
```java
@ManyToMany
@JoinTable(
    name = "Professor_Disciplina",
    joinColumns        = { @JoinColumn(name = "Professor_ID") },
    inverseJoinColumns = { @JoinColumn(name = "Disciplina_ID") }
)
private List<Disciplina> disciplinas;
```
- `@JoinTable` — define o nome da tabela de junção e os nomes das colunas
- `joinColumns` — coluna que referencia a entidade **dona** (Professor)
- `inverseJoinColumns` — coluna que referencia a entidade **inversa** (Disciplina)

**Lado inverso (`Disciplina`):**
```java
@ManyToMany(mappedBy = "disciplinas")
private List<Professor> professores;
```
- `mappedBy = "disciplinas"` — aponta para o campo `disciplinas` em `Professor`; nenhuma tabela extra é criada aqui

---

### 3. Diagrama de relacionamentos

```
Departamento  1 ──── N  Professor  N ──── N  Disciplina
                         (FK: departamento_id)   (tabela: Professor_Disciplina)
```

| Relacionamento | Anotação lado dono | Anotação lado inverso | Estrutura no banco |
|---|---|---|---|
| Departamento → Professor | `@ManyToOne` + `@JoinColumn` | `@OneToMany(mappedBy=...)` | Coluna FK em `Professor` |
| Professor ↔ Disciplina | `@ManyToMany` + `@JoinTable` | `@ManyToMany(mappedBy=...)` | Tabela `Professor_Disciplina` |

---

### 4. Novos Repositories

Seguindo o mesmo padrão da Aula 2 (com `EntityManager`), foram criados repositórios para as novas entidades, com os métodos `save`, `getAll`, `getById` e `delete`:

**`DepartamentoRepository`**
```java
@Repository
public class DepartamentoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Departamento save(Departamento departamento) {
        return entityManager.merge(departamento);
    }

    public List<Departamento> getAll() {
        return entityManager
                .createQuery("from Departamento", Departamento.class)
                .getResultList();
    }
}
```

**`DisciplinaRepository`** e **`ProfessorRepository`** foram expandidos com:
```java
public Disciplina getById(Long id) {
    return entityManager.find(Disciplina.class, id);
}

@Transactional
public void delete(Disciplina disciplina) {
    entityManager.remove(disciplina);
}
```

- `entityManager.find(Classe, id)` — busca um registro pelo ID (retorna `null` se não encontrado)
- `entityManager.remove(entidade)` — remove o registro; a entidade precisa estar no contexto de persistência

---

### 5. Resumo dos conceitos de relacionamento JPA

| Cardinalidade | Anotação | Cria estrutura no banco |
|---|---|---|
| Muitos → Um | `@ManyToOne` | Coluna de FK na tabela dona |
| Um → Muitos | `@OneToMany` | Nenhuma (usa `mappedBy`) |
| Um → Um | `@OneToOne` | Coluna de FK na tabela dona |
| Muitos → Muitos | `@ManyToMany` | Tabela de junção (`@JoinTable`) |

**Conceitos-chave:**
- **Lado dono** — entidade que possui a coluna FK ou a tabela de junção; responsável por persistir a associação
- **Lado inverso** — usa `mappedBy` apontando para o campo do lado dono; somente leitura para o ORM
- **`@JoinColumn`** — personaliza o nome da coluna de chave estrangeira
- **`@JoinTable`** — define a tabela de junção (nome e colunas) para `@ManyToMany`
