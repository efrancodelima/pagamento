# language: pt

Funcionalidade: Notificar checkout do pedido

  Cenário: Sistema de pedido notifica o checkout
    Dado que o sistema de pedido tem uma notificação de checkout para fazer
    Quando o sistema de pagamento receber a notificação
    Então deve criar um pagamento para o pedido
    E retornar o status aguardando pagamento

Funcionalidade: Consulta inválida

  Cenário: Usuário consulta pedido com número inválido
    Dado que o usuário cria uma requisição para consultar o pagamento de um pedido
    Quando o usuário envia a requisição para o endpoint de consulta
    Então o usuário deve receber uma resposta com status code 400
    E a resposta deve conter o motivo do erro
