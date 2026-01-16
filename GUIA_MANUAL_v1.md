# Xuitter API

Clone simples do Twitter desenvolvido com Spring Boot 3.

## 📦 Tecnologias

- **Java 17**
- **Spring Boot 3.5.9**
- **MySQL 8.0**
- **Docker Compose**
- **Flyway** (Migrations)
- **JPA/Hibernate** (ORM)
- **Maven** (Gerenciador de dependências)

## 📚 Índice

1. [Arquitetura do Projeto](#arquitetura-do-projeto)
2. [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
3. [Como Executar](#como-executar)
4. [Endpoints da API](#endpoints-da-api)
5. [Testes](#testes)
6. [Frontend](#frontend)

---

## 🏛️ Arquitetura do Projeto

### Estrutura de Pacotes

```
src/main/java/br/com/xuitter/xuitter_api/
├── controller/          # Controllers REST
│   ├── FeedController.java
│   └── PiuController.java
├── dto/                 # Data Transfer Objects
│   └── XuitResponse.java
├── entities/            # Entidades JPA
│   ├── User.java
│   ├── Xuit.java
│   └── XuitType.java
├── repositories/        # Repositórios JPA
│   ├── UserRepository.java
│   └── XuitRepository.java
└── XuitterApiApplication.java

src/main/resources/
├── db/migration/        # Migrations Flyway
│   ├── V1__Create_user_table.sql
│   └── V2__Create_xuit_table.sql
├── static/              # Frontend
│   └── index.html
└── application.properties

src/test/
├── java/
│   ├── FeedControllerTest.java
│   └── PiuControllerTest.java
└── resources/
    └── application-test.properties
```

### Camadas da Aplicação

1. **Controller** - Recebe requisições HTTP
2. **DTO** - Formata dados para resposta JSON
3. **Repository** - Acessa banco de dados
4. **Entity** - Representa tabelas do banco

---

## 🗄️ Estrutura do Banco de Dados

### Tabela: `user`

| Campo | Tipo | Descrição |
|-------|------|------------|
| id | bigint | Chave primária (auto increment) |
| username | varchar(10) | Nome do usuário (único) |
| created_at | datetime | Data de criação |

### Tabela: `xuit`

| Campo | Tipo | Descrição |
|-------|------|------------|
| id | bigint | Chave primária (auto increment) |
| content | varchar(42) | Conteúdo do post |
| type | enum | ORIGINAL, REXUIT, QUOTE |
| author_id | bigint | FK para user |
| original_xuit_id | bigint | FK para xuit (opcional) |
| created_at | datetime | Data de criação |

### Relacionamentos

- **User 1:N Xuit** - Um usuário pode ter vários xuits
- **Xuit N:1 Xuit** - Um xuit pode referenciar outro (rexuit/quote)

---

## 🚀 Como Executar

### 1. Pré-requisitos

- Java 17 instalado
- Docker Desktop rodando
- Git

### 2. Clonar o repositório

```bash
git clone https://github.com/guilermefalcao/xuitter-api.git
cd xuitter-api
```

### 3. Subir o banco de dados MySQL

```bash
docker-compose up -d
```

Isso criará:
- Container MySQL 8.0
- Banco `xuitter` na porta 3307
- Usuário: `root` / Senha: `root`

### 4. Executar a aplicação

```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

### 5. Inserir dados de teste (opcional)

```bash
# Inserir usuário
docker exec xuitter-mysql mysql -u root -proot xuitter -e "INSERT INTO user (username, created_at) VALUES ('fabricio', NOW());"

# Inserir xuits
docker exec xuitter-mysql mysql -u root -proot xuitter -e "INSERT INTO xuit (content, type, author_id, created_at) VALUES ('siga o ia sob controle', 'ORIGINAL', 1, NOW()), ('siga o xuitterrr 11', 'ORIGINAL', 1, NOW()), ('siga o xuitterrr 22', 'ORIGINAL', 1, NOW());"
```

---

## 🔌 Endpoints da API

### GET /piu

Retorna emoji do pássaro.

**Resposta:**
```
🐦
```

### GET /feed

Retorna lista de xuits em JSON.

**Resposta:**
```json
[
  {
    "id": 1,
    "content": "siga o ia sob controle",
    "type": "ORIGINAL",
    "authorUsername": "fabricio"
  }
]
```

---

## 🧪 Testes

### Executar todos os testes

```bash
./mvnw test
```

### Testes disponíveis

**PiuControllerTest:**
- Testa endpoint `/piu`
- Usa `@WebMvcTest` (sem banco)

**FeedControllerTest:**
- `t1()` - Testa feed completo com validações JSON
- `t2()` - Testa apenas status 200
- Usa `@SpringBootTest` (com banco de teste)
- Banco: `xuitter_test` (criado automaticamente)

### Banco de Testes

O perfil `test` usa:
- Banco separado: `xuitter_test`
- Estratégia: `create-drop` (limpa após cada teste)
- Flyway desabilitado (Hibernate cria tabelas)

---

## 🎨 Frontend

### Acessar interface web

```
http://localhost:8080/
```

### Funcionalidades

- Exibe feed de xuits
- Design tema escuro (estilo Twitter/X)
- Busca dados da API automaticamente
- Responsível

### Tecnologias

- HTML5
- CSS3 (Flexbox)
- JavaScript (Fetch API)

---

## 🛠️ Comandos Úteis

### Docker

```bash
# Ver containers rodando
docker ps

# Ver logs do MySQL
docker logs xuitter-mysql

# Parar containers
docker-compose down

# Parar e remover volumes (apaga dados)
docker-compose down -v
```

### MySQL

```bash
# Conectar no MySQL
docker exec -it xuitter-mysql mysql -u root -p

# Ver tabelas
docker exec xuitter-mysql mysql -u root -proot -e "USE xuitter; SHOW TABLES;"

# Ver dados
docker exec xuitter-mysql mysql -u root -proot -e "USE xuitter; SELECT * FROM xuit;"
```

### Maven

```bash
# Compilar
./mvnw compile

# Limpar build
./mvnw clean

# Executar testes
./mvnw test

# Gerar JAR
./mvnw package
```

---

## 📝 Configurações

### application.properties (Produção)

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/xuitter
spring.datasource.username=root
spring.datasource.password=root
```

### application-test.properties (Testes)

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/xuitter_test?createDatabaseIfNotExist=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.flyway.enabled=false
```

---

## 👥 Autor

**Guilherme Falcão**
- GitHub: [@guilermefalcao](https://github.com/guilermefalcao)

---

## 📝 Licença

Projeto desenvolvido para fins educacionais.
