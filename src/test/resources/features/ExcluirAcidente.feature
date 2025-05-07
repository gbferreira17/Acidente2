# language: pt
@regressivo
Funcionalidade: Deletar um acidente
  Como usuário da API
  Quero conseguir deletar um acidente
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de ocorrência
    Dado que eu tenha os seguintes dados da ocorrência:
      | campo           | valor           |
      | modeloVeiculo   | carro           |
      | placaVeiculo    | abc12           |
      | dataOcorrencia  | 2025-04-21      |
      | endereco        | Av paulista 321 |
    Quando eu enviar a requisição para o endpoint "/acidente" de cadastro de ocorrência
    Então O status code da resposta deve ser 201

  Cenário: Deve ser possível deletar um acidente
    Dado que eu recupere o ID do acidente criada no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/acidente" de deleção de entrega
    Então o status code da resposta dev ser 204