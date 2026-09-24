# Aula 5 — Inversão de Controle, Injeção de Dependência e métodos `@Bean` (23/09/2026)

## Projeto
Spring Boot com JPA — `exemplojpa`

---

## Tópicos abordados

### 1. Inversão de Controle (IoC)

Na aplicação tradicional, uma classe cria diretamente os objetos dos quais precisa. Com a **Inversão de Controle**, essa responsabilidade passa para o Spring.

O Spring cria e gerencia os objetos da aplicação, chamados de **beans**, e controla o ciclo de vida deles. As classes apenas declaram as dependências necessárias.

O container do Spring identifica as configurações e componentes da aplicação durante a inicialização e disponibiliza os beans para injeção.

---

### 2. Injeção de Dependência

**Injeção de Dependência (DI)** é a forma como uma classe recebe os objetos de que precisa, em vez de criá-los com `new`.

Nesta aula, o `ExemploController` recebe três dependências no construtor:

```java
public ExemploController(String nomeApp,
        @Qualifier("versaoApp") Double versaoApp,
        @Qualifier("valorPi") Double valorPi) {
    this.nomeApp = nomeApp;
    this.versaoApp = versaoApp;
    this.valorPi = valorPi;
}
```

A injeção pelo construtor deixa as dependências explícitas e garante que o controller seja criado já com os valores necessários.

#### `@Qualifier`

Como existem dois beans do tipo `Double`, o Spring não conseguiria escolher automaticamente qual deles injetar. `@Qualifier` informa o nome exato de cada bean:

- `@Qualifier("versaoApp")` injeta o bean que contém a versão da aplicação
- `@Qualifier("valorPi")` injeta o bean que contém o valor de π

Sem o `@Qualifier`, ocorreria ambiguidade porque os dois valores possuem o mesmo tipo (`Double`).

---

### 3. Configuração com `@Configuration`

A classe `AppConfig` foi criada para concentrar beans definidos manualmente:

```java
@Configuration
public class AppConfig {
    // métodos @Bean
}
```

`@Configuration` informa ao Spring que a classe contém métodos de configuração. Durante a inicialização, o Spring executa esses métodos e registra os objetos retornados no application context.

---

### 4. Método `@Bean`

Um método anotado com `@Bean` registra no container o objeto retornado pelo método. O nome padrão do bean é o nome do próprio método:

```java
@Bean
public String nomeApp() {
    return "Exemplo JPA";
}

@Bean
public Double versaoApp() {
    return 1.0;
}

@Bean
public Double valorPi() {
    return 3.14159;
}
```

Assim, a aplicação passa a ter os seguintes beans:

| Nome do bean | Tipo | Valor |
|---|---|---|
| `nomeApp` | `String` | `Exemplo JPA` |
| `versaoApp` | `Double` | `1.0` |
| `valorPi` | `Double` | `3.14159` |

O método `@Bean` é útil quando o objeto precisa de uma configuração específica ou quando a classe não possui uma anotação de componente do Spring.

---

### 5. Controller e endpoints

O `ExemploController` foi criado como um controller REST:

```java
@RestController
public class ExemploController {
    // dependências injetadas pelo construtor
}
```

Os endpoints retornam os valores dos beans injetados:

| Endpoint | Retorno |
|---|---|
| `GET /nomeApp` | Nome da aplicação |
| `GET /versao` | Versão da aplicação |
| `GET /valorPi` | Valor de π |

Exemplos de respostas:

```text
GET http://localhost:8081/nomeApp -> Exemplo JPA
GET http://localhost:8081/versao  -> 1.0
GET http://localhost:8081/valorPi -> 3.14159
```

O controller não cria esses valores. Ele apenas recebe os beans e os disponibiliza por meio da API HTTP.

---

### 6. Configuração de CORS com um método `@Bean`

A classe `CorsConfig` também usa `@Configuration` e declara um bean do tipo `WebMvcConfigurer`:

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }
}
```

Essa configuração permite requisições de qualquer origem, com qualquer método HTTP e qualquer cabeçalho. Como as credenciais estão desabilitadas, cookies não são enviados nas requisições cross-origin.

O exemplo mostra que `@Bean` também pode registrar uma implementação anônima de uma interface configurada pelo Spring MVC.

---

### 7. Ajuste no relacionamento com `Departamento`

Foi importado `FetchType` em `Departamento` e deixada uma possibilidade de configuração comentada no relacionamento:

```java
@OneToMany(mappedBy = "departamento"/* , fetch=FetchType.EAGER */)
private List<Professor> professores;
```

O relacionamento continua usando o comportamento padrão. O comentário registra a alternativa `FetchType.EAGER`, que faria os professores serem carregados junto com o departamento. Essa alteração foi apenas experimental e não é o foco principal da aula.

---

## Resumo dos conceitos

| Conceito | Finalidade |
|---|---|
| Inversão de Controle | Transfere para o Spring o controle da criação e do ciclo de vida dos objetos |
| Injeção de Dependência | Fornece às classes os objetos de que elas precisam |
| `@Configuration` | Marca uma classe que contém configurações de beans |
| `@Bean` | Registra no container o objeto retornado por um método |
| `@Qualifier` | Escolhe um bean específico quando há mais de um candidato do mesmo tipo |
| `@RestController` | Expõe uma classe como controller de uma API REST |
| CORS | Controla requisições feitas por aplicações de outras origens |

**Ideia principal:** o controller depende de abstrações e objetos fornecidos pelo Spring. Ele não conhece a lógica de criação desses objetos, o que reduz o acoplamento e facilita a configuração e a manutenção da aplicação.

---

## Arquivos envolvidos na aula

- `src/main/java/com/example/exemplojpa/configurations/AppConfig.java`
  - declara os beans `nomeApp`, `versaoApp` e `valorPi`
- `src/main/java/com/example/exemplojpa/configurations/CorsConfig.java`
  - registra a configuração de CORS como bean
- `src/main/java/com/example/exemplojpa/controllers/ExemploController.java`
  - recebe os beans por injeção no construtor
  - disponibiliza os endpoints `/nomeApp`, `/versao` e `/valorPi`
- `src/main/java/com/example/exemplojpa/models/Departamento.java`
  - registra o ajuste experimental relacionado a `FetchType`
- `Aula5.md`
  - documenta os conceitos e as alterações da aula