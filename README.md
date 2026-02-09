# Desafio Técnico - Backend

Solução de Backend para o desafio técnico, desenvolvida com **Arquitetura de Microsserviços** utilizando o ecossistema Spring Cloud.

O projeto foi estruturado para garantir escalabilidade, desacoplamento e fácil manutenção.

---

## 🏗️ Arquitetura do Sistema

O sistema é orquestrado por 4 microsserviços distintos:

1.  **Service Discovery (Eureka Server)**
    * **Porta:** 8761
    * **Função:** Servidor de registro e descoberta. Todos os microsserviços se registram aqui (`@EnableEurekaServer`) para que possam se comunicar sem saber IPs fixos.
    * **Pacote:** `com.apirest.service_discovery`

2.  **API Gateway**
    * **Porta:** 8080
    * **Função:** Porta de entrada única do sistema. Utiliza o *Spring Cloud Gateway* para rotear requisições do frontend/clientes para os serviços competentes (Usuario ou Cartao), baseando-se no registro do Eureka.
    * **Pacote:** `com.apirest.api_gateway`

3.  **Usuario Service**
    * **Porta:** 8081 (Executar nesta porta para não conflitar com o Gateway)
    * **Função:** Gerencia o domínio de Pessoas/Usuários, regras de negócio de cadastro e perfis de acesso.
    * **Pacote:** `com.apirest.usuario`

4.  **Cartao Service**
    * **Porta:** 8082
    * **Função:** Gerencia a bilhetagem (Cartão VEM). Possui um *Feign Client* que se comunica com o *Usuario Service* para validar a existência do usuário antes de emitir um cartão.
    * **Pacote:** `com.apirest.cartao`

---

## 🚀 Tecnologias e Padrões

* **Java 21 (LTS)**
* **Spring Boot 3.4+**
* **Spring Cloud** (Eureka, Gateway, OpenFeign)
* **MySQL 8** (Banco de Dados Relacional)
* **Flyway** (Versionamento e Migração de Banco de Dados)
* **Spring Data JPA** (Persistência)
* **MapStruct** (Mapeamento performático de DTOs)
* **SpringDoc OpenAPI** (Documentação Swagger)
* **Padrão DTO** (Request/Response isolados da Entidade)
* **Arquitetura em Camadas** (Controller, Service, Repository)

---

## 🛠️ Como Rodar o Ecossistema

### 1. Preparação do Ambiente
* Certifique-se de ter o **Java 21** e **Maven** instalados.
* Tenha um banco **MySQL** rodando na porta 3306.
* Configure a variável de ambiente com a senha do seu banco:
    * Linux/Mac: `export MYSQL_PASSWORD=sua_senha`
    * Windows: `$env:MYSQL_PASSWORD="sua_senha"`

## ▶️ Ordem de Execução (Via IDE)

Para o sistema funcionar, os serviços precisam subir numa ordem específica para que se registrem no Eureka corretamente.

Abra sua IDE (IntelliJ ou Eclipse), localize a classe principal (`Application.java`) de cada projeto e clique no botão **Run/Play** (▶) na seguinte ordem:

### 1º - Service Discovery (Eureka)
* **Arquivo:** `src/main/java/com/apirest/service_discovery/ServiceDiscoveryApplication.java`
* **Ação:** Clique em **Run**.
* **Verificação:** Aguarde iniciar. Acesse [http://localhost:8761](http://localhost:8761) e veja o painel do Eureka.

### 2º - Usuario Service
* **Arquivo:** `src/main/java/com/apirest/usuario/UsuarioServiceApplication.java`
* **Ação:** Clique em **Run**.
* **Nota:** Verifique no console se subiu na porta **8081**.

### 3º - Cartao Service
* **Arquivo:** `src/main/java/com/apirest/cartao/CartaoServiceApplication.java`
* **Ação:** Clique em **Run**.
* **Nota:** Vai subir na porta padrão **8082**.

### 4º - API Gateway
* **Arquivo:** `src/main/java/com/apirest/api_gateway/ApiGatewayApplication.java`
* **Ação:** Clique em **Run**.
* **Nota:** Vai subir na porta **8080**.

---

## 📡 Acesso aos Serviços (Links e Rotas)

Você pode testar a API de duas formas: centralizada (como o Frontend fará) ou individualmente (para debug).

### 1. Via API Gateway (Centralizado - Porta 8080)
Use estas URLs no Postman ou no Frontend. O Gateway redireciona para o serviço correto.

| Recurso | Método | URL Completa |
| :--- | :---: | :--- |
| **Listar Usuários** | GET | `http://localhost:8080/usuario-service/usuario` |
| **Criar Usuário** | POST | `http://localhost:8080/usuario-service/usuario` |
| **Listar Cartões** | GET | `http://localhost:8080/cartao-service/cartao` |
| **Criar Cartão** | POST | `http://localhost:8080/cartao-service/cartao` |

### 2. Via Swagger UI (Documentação Visual)
Acesse a documentação interativa de cada microsserviço individualmente:

* 📘 **Usuario Service Swagger:**
  [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

* 📙 **Cartao Service Swagger:**
  [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

  ### 3. Via Front-End
  Acesse https://github.com/almirAlmir/urbanatestevisual e rode o frontend após todas as rotas do backend estarem funcionando corretamente.

---

## ✅ Checklist de Requisitos (Status do Projeto)


### 1. Funcionalidades de Negócio
| Funcionalidade | Implementado? | Observação                                         |
| :--- | :---: |:---------------------------------------------------|
| CRUD Usuário (Listar, Incluir, Alterar, Remover) | ✅ SIM | Completo no `usuario-service`                      |
| Estrutura de Dados (Usuario e Cartão) | ✅ SIM | Atributos e relacionamentos conforme especificados |
| Adicionar/Remover cartão de usuário | ✅ SIM | Via endpoint dedicado no `cartao-service`          |
| Consultar todos os cartões | ✅ SIM | Com filtro por usuário implementado                |
| Ativar/Inativar cartão | ✅ SIM | Endpoint PATCH implementado                        |

### 2. Requisitos Técnicos Obrigatórios
| Requisito | Implementado? | Observação                              |
| :--- | :---: |:----------------------------------------|
| Java 8+ / Spring Boot | ✅ SIM | Java 21 + Spring Boot 3.4               |
| Build com Maven | ✅ SIM | Projeto Multi-módulo                    |
| Persistência JPA/SQL | ✅ SIM | Hibernate/JPA + MySQL                   |
| Aplicação em Camadas | ✅ SIM | Controller -> Service -> Repository     |
| Angular 4+ | ⏳ EM BREVE | Em repositório separado para o frontend |
| Git Online | ✅ SIM | Código versionado e hospedado           |

### 3. Requisitos Diferenciais (Pontos Extras)
| Requisito | Implementado? | Observação |
| :--- | :---: | :--- |
| **Padrão de Microserviços** | ✅ SIM | Arquitetura completa (Eureka + Gateway + Feign) |
| **Migração de Tabelas** | ✅ SIM | **Flyway** gerencia esquema (V1, V2, V3) |
| **Swagger na API** | ✅ SIM | OpenAPI 3 configurado |
| **Evitar Lombok** | ✅ SIM | Getters/Setters e Construtores manuais |
| **Padrão DTO** | ✅ SIM | Request/Response DTOs em todas as pontas |
| **Criação de Login** | ⚠️ PARCIAL | Backend preparado (busca segura), Auth pendente |
| **Perfil com Permissão** | ⚠️ PARCIAL | Campo `tipo` existe, falta bloqueio de rota |
| **Native Query** | ⚪ NÃO | Optou-se por *Query Methods* padrão |

---
**Desenvolvido por:** [Almir Alves de Souza Cruz]
