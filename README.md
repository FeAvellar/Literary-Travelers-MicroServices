
# Literary Travelers

## **Visão Geral**
O **Literary Travelers MicroServices** é um sistema RESTful desenvolvido em Java com Spring Boot e Hibernate. 
Ele oferece uma solução escalável para gerenciamento de usuários, incluindo autenticação, controle de acesso baseado 
em papéis (*roles*), validação de dados e upload de arquivos.

### **Principais Funcionalidades**
- Cadastro, atualização e exclusão de usuários.
- Gerenciamento de papéis (*roles*) e permissões.
- Validações customizadas para dados de entrada.
- Configuração de segurança para autenticação e autorização.
- Suporte a upload de fotos.
- Testes unitários para garantir a qualidade do código.

---

## **Tabela de Conteúdos**
1. [Estrutura do Projeto](#estrutura-do-projeto)
2. [Endpoints Disponíveis](#endpoints-disponíveis)
3. [Configuração do Ambiente](#configuração-do-ambiente)
4. [Testes](#testes)
5. [Contribuindo](#contribuindo)
6. [Licença](#licença)

---

## **Estrutura do Projeto**

```
src/
|-- main/
|   |-- java/
|   |   |-- com/
|   |       |-- literarytravelers/
|   |           |-- user/
|   |               |-- controller/   # Controladores REST
|   |               |-- entities/     # Entidades JPA
|   |               |-- exceptions/   # Classes para tratamento de exceções
|   |               |-- repository/   # Repositórios para acesso ao banco de dados
|   |               |-- security/     # Configuração de segurança
|   |               |-- service/      # Lógica de negócios
|   |               |-- UserApplication.java # Classe principal
|-- resources/
    |-- db/                          # Scripts de migração Flyway
    |-- application.properties       # Configurações do Spring Boot
```

### **Principais Pacotes**
1. **controller/**: Gerencia as requisições HTTP e direciona as respostas adequadas.
2. **entities/**: Define as tabelas e relações do banco de dados usando anotações JPA.
3. **service/**: Contém a lógica de negócios, como validação e regras específicas.
4. **security/**: Configuração para autenticação e autorização com Spring Security.

---

## **Endpoints Disponíveis**

### **UserController**
- **`GET /users`**  
  Retorna todos os usuários cadastrados.  
  **Resposta:**  
  ```json
  [
    {
      "id": 1,
      "name": "João Silva",
      "email": "joao@example.com"
    }
  ]
  ```

- **`POST /users`**  
  Cria um novo usuário.  
  **Exemplo de Corpo:**  
  ```json
  {
    "name": "Maria",
    "email": "maria@example.com",
    "password": "12345"
  }
  ```

- **`PUT /users/{id}`**  
  Atualiza os dados de um usuário existente.

- **`DELETE /users/{id}`**  
  Remove um usuário do sistema.

### **RoleController**
- **`GET /roles`**  
  Retorna todos os papéis.

- **`POST /roles`**  
  Cria um novo papel no sistema.

---

## **Configuração do Ambiente**

### **Pré-requisitos**
- Java 17+
- Maven 3.6+
- Banco de dados relacional (ex.: PostgreSQL)

### **Passos para Configuração**
1. Clone o repositório:
   ```bash
   git clone https://github.com/FeAvellar/Literary-Travelers-MicroServices.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd Literary-Travelers-MicroServices
   ```
3. Configure as variáveis de ambiente no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literary_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
4. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

---

## **Testes**
Os testes estão localizados no pacote `src/test/java`. Para executar todos os testes, utilize:
```bash
mvn test
```

### **Frameworks Utilizados**
- JUnit 5
- Mockito

### **Cobertura de Testes**
- Métodos do serviço: `UserService` e `RoleService`
- Endpoints do controlador: `UserController` e `RoleController`

---

## **Contribuindo**
Contribuições são bem-vindas! Para colaborar:
1. Faça um *fork* do repositório.
2. Crie uma nova *branch* para suas alterações:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça um *commit* das suas alterações:
   ```bash
   git commit -m "Descrição da alteração"
   ```
4. Envie suas alterações:
   ```bash
   git push origin minha-feature
   ```
5. Abra um *pull request* na página do repositório.

---

## **Licença**
Este projeto é licenciado sob a [MIT License](LICENSE).
