# language: pt

Funcionalidade: Notificar checkout do pedido

  Cenário: Sistema de pedido notifica o checkout
    Dado que o sistema de pedido tem uma notificação de checkout para fazer
    Quando o sistema de pagamento receber a notificação
    Então deve retornar o status aguardando pagamento
