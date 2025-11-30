# Stockapp Python

Uma API FastAPI para predições de ações usando algoritmos de Machine Learning como Random Forest e Regressão Linear.

## 📁 Estrutura do Projeto

### Arquivos Principais

- **`main.py`** - Ponto de entrada da aplicação FastAPI com configurações de CORS e inicialização do servidor
- **`core/configs.py`** - Configurações centralizadas da aplicação (porta, host, prefixos de API)
- **`schemas/prediction_schema.py`** - Schemas Pydantic para validação de dados de entrada e resposta
- **`api/v1/api.py`** - Roteador principal que agrupa todos os endpoints da API v1
- **`api/v1/routers/random_forest.py`** - Endpoints específicos para predições usando Random Forest
- **`api/v1/routers/linear_regression.py`** - Endpoints específicos para predições usando Regressão Linear
- **`api/v1/services/predicition_service_model.py`** - Classe base para modelos de predição
- **`api/v1/services/random_forest_service.py`** - Serviço para predições com Random Forest
- **`api/v1/services/linear_regression_service.py`** - Serviço para predições com Regressão Linear

### Estrutura de Diretórios
```
stockapp-python/
├── main.py
├── requirements.txt
├── .env
├── .env.example
├── .gitignore
├── .gitattributes
├── core/
│   └── configs.py
├── schemas/
│   └── prediction_schema.py
├── ml_models/
│   ├── random_forest_model_features_2025_10_17.pkl
│   └── linear_regression_model_features_2025_10_17.pkl
└── api/
    └── v1/
        ├── api.py
        ├── services/
        │   ├── predicition_service_model.py
        │   ├── random_forest_service.py
        │   └── linear_regression_service.py
        └── routers/
            ├── linear_regression.py
            └── random_forest.py
```

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- Python 3.12.4
- pip
- Git LFS (para os arquivos de modelo .pkl)

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd stockapp-python
   ```

2. **Baixe os modelos ML com Git LFS**
   ```bash
   git lfs pull
   ```

3. **Crie um ambiente virtual**
   ```bash
   python -m venv venv
   ```

4. **Ative o ambiente virtual**
   - Windows:
     ```bash
     venv\Scripts\activate
     ```
   - Linux/macOS:
     ```bash
     source venv/bin/activate
     ```

5. **Instale as dependências**
   ```bash
   pip install -r requirements.txt
   ```

6. **Configure as variáveis de ambiente**
   
   Faça uma cópia do .env.example em um arquivo chamado .env
   ```bash
   cp .env.example .env
   ```
   Edite o arquivo `.env` com suas configurações:
   ```
   PORT=8000
   HOST=127.0.0.1
   ```

7. **Execute a aplicação**
   ```bash
   python main.py
   ```

8. **Acesse a aplicação**
   - API: http://{HOST}:{PORT}
   - Documentação Swagger: http://{HOST}:{PORT}/docs
   - Documentação ReDoc: http://{HOST}:{PORT}/redoc

## 📋 Mapeamento dos Endpoints

### Base URL
```
http://{HOST}:{PORT}/api/v1
```

### Endpoints Disponíveis

#### 1. Random Forest - Predição
- **URL:** `POST /api/v1/rf/prediction`
- **Descrição:** Realiza predições de valores máximo e mínimo de ações usando algoritmo Random Forest
- **Request Body:**
  ```json
  {
    "ticker": "TICKER",
    "history": [
      {
        "date": "YYYY-MM-DD",
        "open": 0.0,
        "high": 0.0,
        "low": 0.0,
        "close": 0.0,
        "adj_close": 0.0,
        "volume": 0.0,
        "dividend_yield": 0.0
      },
      // ... exatamente 5 dias de histórico
    ]
  }
  ```
- **Response:**
  ```json
  {
    "high": 0.0,
    "low": 0.0
  }
  ```

#### 2. Linear Regression - Predição
- **URL:** `POST /api/v1/lr/prediction`
- **Descrição:** Realiza predições de valores máximo e mínimo de ações usando algoritmo de Regressão Linear
- **Request Body:** *(mesmo formato do Random Forest)*
- **Response:** *(mesmo formato do Random Forest)*

## 📝 Schemas de Dados

### StockHistory
```python
{
    "date": str,           # Data no formato string (YYYY-MM-DD)
    "open": float,         # Preço de abertura
    "high": float,         # Preço máximo do dia
    "low": float,          # Preço mínimo do dia
    "close": float,        # Preço de fechamento
    "adj_close": float,    # Preço de fechamento ajustado
    "volume": float,       # Volume de transações
    "dividend_yield": float # Rendimento de dividendos
}
```

### PredictionCreateBase
```python
{
    "ticker": str,                    # Símbolo da ação (ex: "AAPL", "GOOGL")
    "history": List[StockHistory]     # Lista com exatamente 5 dias de histórico
}
```

### PredictionResponseBase
```python
{
    "high": float,    # Predição do valor máximo para o próximo dia
    "low": float      # Predição do valor mínimo para o próximo dia
}
```

## 🤖 Modelos de Machine Learning

### Arquitetura dos Serviços

A aplicação utiliza uma arquitetura baseada em serviços para os modelos de ML:

- **PredictionBaseModel**: Classe base que carrega modelos treinados (.pkl) e fornece funcionalidade de predição
- **RandomForestPrediction**: Implementação específica para modelo Random Forest
- **LinearRegressionPrediction**: Implementação específica para modelo de Regressão Linear

### Características dos Modelos

- **Entrada**: Histórico de 5 dias de dados de ações com features de lag
- **Saída**: Predição de valores máximo e mínimo para o próximo dia
- **Features**: Utiliza lags de 1 a 5 dias para todas as variáveis (open, high, low, close, adj_close, volume, dividend_yield)

## 🔧 Configurações

As configurações da aplicação são gerenciadas através do arquivo `.env`:

- `PORT` - Porta onde a aplicação será executada
- `HOST` - Host onde a aplicação será executada

## ⚠️ Requisitos Importantes

1. **Histórico de Dados**: É obrigatório fornecer exatamente 5 dias de histórico para realizar predições
2. **Git LFS**: Os modelos ML (.pkl) são armazenados usando Git LFS devido ao tamanho dos arquivos
3. **Ordem dos Dados**: Os dados devem estar ordenados por data (mais antigo para mais recente)

## 🚨 Tratamento de Erros

A API retorna códigos de status apropriados:

- **200**: Sucesso na predição
- **400**: Dados de entrada inválidos (ex: quantidade incorreta de dias no histórico)
- **500**: Erro interno do servidor durante o processamento da predição



