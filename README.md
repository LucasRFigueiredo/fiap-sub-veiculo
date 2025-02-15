# 🚗 Veículos API

## Descrição do Projeto
A **Veículos API** é um microsserviço desenvolvido em **Java 21** com **Spring Boot 3.4.1** para gerenciar o cadastro e a listagem de veículos. Ele permite a inclusão, exclusão, atualização e consulta de veículos.

---

## 📚 Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.1
- PostgreSQL
- Docker
- GitHub Actions (CI/CD)
- AWS ECR (Elastic Container Registry)

---

## 📂 Estrutura do Projeto
```
Veículos/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com.sub.veiculo/
│   │           ├── application/
│   │           │   ├── port/
│   │           │   │   ├── input/
│   │           │   │   └── output/
│   │           │   └── service/
│   │           ├── domain/
│   │           ├── infrastructure/
│   │           │   ├── adapter/
│   │           │   ├── controller/
│   │           │   └── configuration/
│   │           └── VeiculoApplication.java
│   └── resources/
│       └── application.properties
│
├── Dockerfile
├── README.md
└── .github/
    └── workflows/
        └── deploy.yml
```

---

## 🚀 Como Rodar Localmente

### 1️⃣ Clonar o repositório
```sh
git clone https://github.com/LucasRFigueiredo/fiap-sub-veiculo.git
cd veiculos-api
```

### 2️⃣ Configurar o banco de dados
- Banco de dados PostgreSQL hospedado na AWS RDS.
- Atualize o arquivo `application.properties` com suas credenciais.

### 3️⃣ Build e execução
```sh
mvn clean package
java -jar target/veiculo-0.0.1-SNAPSHOT.jar
```

---

## 📦 Docker
### Build e execução com Docker
```sh
docker build -t veiculos-api .
docker run -p 8080:8080 veiculos-api
```

---

## 📬 Endpoints Principais
- **GET** `/vehicles` - Listar todos os veículos
- **POST** `/vehicles` - Cadastrar um novo veículo
- **GET** `/vehicles/{id}` - Consultar veículo por ID
- **DELETE** `/vehicles/{id}` - Remover veículo por ID

