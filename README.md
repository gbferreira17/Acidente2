# API ACIDENTE

Aplicação API 

## Pré-requisitos

- Java 21
- Git
- Docker
- Insomnia

## Build e execução

```sh
docker compose up --build
```

## Testes unitários (validação)

./mvnw test


## Insomnia
POST - Registrar usuário
http://localhost:8080/auth/register
{
	"nome":"usuario10",
	"email":"usuarioteste3@email.com",
	"password":"123456",
	"role":"ADMIN"
	
}

POST - acesso (Retorna um token)
http://localhost:8080/auth/login
{
	"email":"usuarioteste3@email.com",
	"password":"123456"
	
}

GET - Listar todos
http://localhost:8080/acidente

POST - Cadastrar 
http://localhost:8080/acidente
{
	"modeloVeiculo":"carro",
	"placaVeiculo":"456dfg",
	"dataOcorrencia":"2025-04-21",
	"endereco":"Av paulista 456"
}

PUT - Alterar
http://localhost:8080/acidente
	{
	"acidenteId": 1,
	"modeloVeiculo": "onibus",
	"placaVeiculo": "abc12",
	"dataOcorrencia": "2024-10-30",
	"endereco": "Av paulista 321"
	}

DELET - Excluir
http://localhost:8080/acidente/1






