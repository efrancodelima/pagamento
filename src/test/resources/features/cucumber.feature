Feature: Requisição inválida

  Scenario: Usuário consulta pedido com número inválido
    Given que o usuário cria uma requisição para consultar o pagamento de um pedido
    When o usuário envia a requisição para o endpoint de consulta
    Then o usuário deve receber uma resposta com status code 400
    And uma mensagem informando o motivo do erro
