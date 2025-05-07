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




  Cenário: Cadastro sem sucesso ao faltar campo
    Dado que eu tenha os seguintes dados da ocorrência:
      | campo           | valor           |
      | modeloVeiculo   |                 |
      | placaVeiculo    | abc12           |
      | dataOcorrencia  | 2025-04-21      |
      | endereco        | Av paulista 321 |
    Quando eu enviar a requisição para o endpoint "/acidente" de cadastro de ocorrência
    Então O status code da resposta deve ser 400
    E O corpo de resposta de erro da api deve retornar a mensagem "O modelo do veículo é obrigatório!"
