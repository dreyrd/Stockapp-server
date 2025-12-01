# Stockapp Server

Sistema completo de predição de ações com Machine Learning, composto por três aplicações containerizadas.

## 🏗️ Arquitetura

O projeto é dividido em três microserviços:

- **Stockapp-python**: API FastAPI com modelos de Machine Learning (Random Forest e Linear Regression)
- **Stockapp-java**: Backend Spring Boot para lógica de negócio e integração
- **Stockapp-angular**: Frontend em Angular com interface de usuário

## 🚀 Como executar com Docker Compose

### Pré-requisitos
- Docker 20.10+
- Docker Compose 2.0+
- Git LFS (para modelos ML)

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd stockapp-server
   ```

2. **Baixe os modelos ML**
   ```bash
   cd Stockapp-python
   git lfs pull
   cd ..
   ```

3. **Inicie todos os containers**
   ```bash
   docker-compose up -d
   ```

4. **Verifique o status dos containers**
   ```bash
   docker-compose ps
   ```

5. **Visualize os logs**
   ```bash
   # Todos os serviços
   docker-compose logs -f
   
   # Serviço específico
   docker-compose logs -f stockapp-python
   docker-compose logs -f stockapp-java
   docker-compose logs -f stockapp-angular
   ```

## 🌐 Acessando as Aplicações

Após iniciar os containers:

- **Frontend Angular**: http://localhost
- **API Java (Spring Boot)**: http://localhost:8080
- **API Python (FastAPI)**: http://localhost:8000
- **Documentação Swagger (Python)**: http://localhost:8000/docs

## 🛠️ Comandos Úteis

```bash
# Parar todos os containers
docker-compose down

# Parar e remover volumes
docker-compose down -v

# Rebuild de todos os containers
docker-compose build

# Rebuild e restart
docker-compose up -d --build

# Restart de um serviço específico
docker-compose restart stockapp-python

# Ver logs em tempo real
docker-compose logs -f

# Executar comando em um container
docker-compose exec stockapp-python /bin/bash
docker-compose exec stockapp-java /bin/sh
docker-compose exec stockapp-angular /bin/sh
```

## 📊 Monitoramento

Health checks estão configurados para todos os serviços:

```bash
# Verificar saúde dos containers
docker-compose ps
```

## 🔧 Variáveis de Ambiente

Cada serviço pode ser configurado através de variáveis de ambiente no `docker-compose.yml`:

### Stockapp-python
- `PORT`: Porta da aplicação (padrão: 8000)
- `HOST`: Host da aplicação (padrão: 0.0.0.0)

### Stockapp-java
- `SPRING_PROFILES_ACTIVE`: Profile do Spring (padrão: prod)
- `PYTHON_API_URL`: URL da API Python

### Stockapp-angular
- `JAVA_API_URL`: URL da API Java

## 🌐 Rede

Todos os containers compartilham a rede `stockapp-network`, permitindo comunicação entre eles usando os nomes dos serviços.

## 📁 Estrutura do Projeto

