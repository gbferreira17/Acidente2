# language: pt
@regressivo
Funcionalidade: Cadastro de novo acidente
  Como usuário da API
  Quero cadastrar um novo acidente


  Cenário: Cadastro bem-sucedido de ocorrência
    Dado que eu tenha os seguintes dados da ocorrência:
      | campo           | valor           |
      | modeloVeiculo   | carro           |
      | placaVeiculo    | abc12           |
      | dataOcorrencia  | 2025-04-21      |
      | endereco        | Av paulista 321 |
    Quando eu enviar a requisição para o endpoint "/acidente" de cadastro de ocorrência
    Então O status code da resposta deve ser 201
    E  o arquivo de contrato esperado é o "Cadastro bem-sucedido de acidente"
    Então  resposta da requisição deve estar em conformidade com o contrato selecionado