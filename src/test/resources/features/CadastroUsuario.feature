# language: pt
@regressivo
Funcionalidade: Cadastro de novo usuário
  Como usuário da API
  Quero cadastrar um novo usuário
  Para que ele possa acessar o sistema com seu perfil

  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo     | valor                     |
      | nome      | usuario10                 |
      | email     | usuarioteste5677@email.com  |
      | password  | 123456                    |
      | role      | ADMIN                     |
    Quando eu enviar a requisição para o endpoint "/register" de cadastro de usuário
    Então o status code da resposta deve ser 201


  Cenário: Cadastro de usuário sem sucesso ao passar um e-mail inválido
    Dado que eu tenha os seguintes dados do usuário:
      | campo     | valor                     |
      | nome      | usuario10                 |
      | email     | usuarioteste12email.com   |
      | password  | 123456                    |
      | role      | ADMIN                     |
    Quando eu enviar a requisição para o endpoint "/register" de cadastro de usuário
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "O e-mail do usuário não é válido!"

