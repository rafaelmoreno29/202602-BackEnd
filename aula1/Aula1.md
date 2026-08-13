# Aula 1 — Introdução ao Back-End com Spring Boot

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Web MVC** (`spring-boot-starter-webmvc`)
- **Maven** (gerenciador de dependências e build)

---

## O que foi feito

### 1. Criação do projeto Spring Boot

O projeto foi gerado com a estrutura padrão do Spring Initializr, contendo:

- Classe principal `Aula1Application` anotada com `@SpringBootApplication`, responsável por inicializar o servidor embutido.
- Dependência `spring-boot-starter-webmvc` para criação de APIs REST.

---

### 2. Primeiro Controller REST — `MeuController`

Foi criada a classe `MeuController` anotada com `@RestController`, que expõe os seguintes endpoints:

| Método | Rota             | Descrição                                      |
|--------|------------------|------------------------------------------------|
| GET    | `/`              | Retorna `"Olá Mundo!"`                         |
| GET    | `/com-nome`      | Recebe `nome` e `s` (sobrenome) via query param e retorna uma saudação personalizada |
| GET    | `/cliente/{id}`  | Recebe um `id` via path variable e retorna o identificador do cliente |
| POST   | `/calcular`      | Recebe um JSON com operação e números, retorna o resultado |

#### Conceitos abordados

- `@GetMapping` — mapeia requisições HTTP GET
- `@PostMapping` — mapeia requisições HTTP POST
- `@RequestParam` — lê parâmetros da query string (ex: `?nome=João&s=Silva`)
- `@PathVariable` — lê variáveis embutidas na URL (ex: `/cliente/42`)
- `@RequestBody` — desserializa o corpo JSON da requisição para um objeto Java

---

### 3. DTO de Calculadora — `CalculadoraDTO`

Foi criada a classe `CalculadoraDTO` no pacote `models`, representando o corpo da requisição e da resposta do endpoint `/calcular`.

**Campos:**

| Campo       | Tipo     | Descrição                          |
|-------------|----------|------------------------------------|
| `numero1`   | `Double` | Primeiro operando                  |
| `numero2`   | `Double` | Segundo operando                   |
| `operacao`  | `String` | Operação desejada                  |
| `resultado` | `Double` | Resultado calculado (resposta)     |

**Operações suportadas:**

| Valor de `operacao` | Operação        |
|---------------------|-----------------|
| `"soma"`            | numero1 + numero2 |
| `"subtracao"`       | numero1 - numero2 |
| `"multiplicacao"`   | numero1 * numero2 |
| `"divisao"`         | numero1 / numero2 |

**Exemplo de requisição POST `/calcular`:**

```json
{
  "numero1": 10,
  "numero2": 4,
  "operacao": "soma"
}
```

**Resposta:**

```json
{
  "numero1": 10.0,
  "numero2": 4.0,
  "operacao": "soma",
  "resultado": 14.0
}
```

---

## Estrutura de pacotes

```
com.example.aula1
├── Aula1Application.java       ← ponto de entrada da aplicação
├── controllers/
│   └── MeuController.java      ← endpoints REST
└── models/
    └── CalculadoraDTO.java     ← objeto de transferência de dados
```

---

## Conceitos-chave da aula

- **Spring Boot** — framework que simplifica a configuração de aplicações Java, incluindo servidor HTTP embutido (Tomcat).
- **REST API** — estilo de arquitetura para comunicação entre cliente e servidor usando HTTP.
- **`@RestController`** — combina `@Controller` + `@ResponseBody`, fazendo com que os métodos retornem dados (JSON) diretamente.
- **DTO (Data Transfer Object)** — padrão de projeto para transportar dados entre camadas ou entre cliente e servidor.
- **Serialização/Desserialização JSON** — o Spring Boot converte automaticamente objetos Java para JSON (e vice-versa) usando Jackson.
