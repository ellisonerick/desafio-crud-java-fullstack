# Desafio CRUD Java Fullstack - Nexum

**Autor:** Ellison Erick Diniz da Silva  
**Tecnologias utilizadas:** Java (Spring Boot), Vue.js, Bootstrap, Axios, Maven

---

## 🧠 Sobre o projeto

Este projeto foi desenvolvido como parte do **Desafio Técnico Fullstack da Nexum**, com o objetivo de criar uma aplicação completa de **cadastro de pessoas (CRUD)** — incluindo operações de criação, listagem, edição e exclusão de registros.

A aplicação é composta por um **backend em Java (Spring Boot)** e um **frontend em Vue.js**, conectados via API REST.

---

## ⚙️ Funcionalidades

- ➕ Cadastrar nova pessoa  
- 📋 Listar todas as pessoas cadastradas  
- ✏️ Editar informações de uma pessoa  
- ❌ Excluir pessoa com confirmação  
- 🧭 Interface responsiva e moderna com Bootstrap  

---

## 🏗️ Estrutura do projeto

```bash
Projeto/
├── backend/
│   └── pessoas/
│       ├── src/
│       ├── pom.xml
│       └── ...
└── frontend/
    ├── src/
    ├── package.json
    └── ...
```
## 🚀 Como executar o projeto localmente

### 🔹 **Pré-requisitos**
Certifique-se de ter instalado em sua máquina:
- [Java JDK 17+](https://www.oracle.com/br/java/technologies/downloads/)
- [Maven](https://maven.apache.org/)
- [Node.js (versão 18+)](https://nodejs.org/)
- [Git](https://git-scm.com/)

---

### 🔹 **Clonar o repositório**

```bash
git clone https://github.com/ellisonerick/desafio-crud-java-fullstack.git
cd desafio-crud-java-fullstack/Projeto
```
🔹 Rodar o backend (Spring Boot)
```bash
cd backend/pessoas
mvn spring-boot:run
```
O backend será iniciado em:

http://localhost:8080

🔹 Rodar o frontend (Vue.js)

Em outro terminal:
```bash
cd frontend
npm install
npm run dev
```
O frontend será iniciado em:

http://localhost:5173


🧩 Integração entre backend e frontend

O frontend consome os endpoints da API REST configurada no backend.
Certifique-se de que o Spring Boot esteja rodando antes de iniciar o Vue.js, para que as requisições funcionem corretamente.


🖼️ Interface

A aplicação conta com:

Uma Navbar de navegação (Listar / Cadastrar)

Tabelas e formulários com Bootstrap

Alertas visuais de sucesso e erro

Modal de confirmação para exclusão


💡 Observação

O projeto foi desenvolvido com foco em boas práticas, organização e clareza de código, refletindo o processo completo de criação de uma aplicação fullstack.


👨‍💻 Autor
Ellison Erick Diniz da Silva
Desenvolvedor Fullstack | Engenheiro Mecânico
📍 Brasil
🌐 GitHub

⭐ Se este projeto foi útil, deixe uma estrela no repositório!
