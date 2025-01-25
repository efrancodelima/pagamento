package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  NUMERO_PEDIDO("O número do pedido é inválido."),
  VALOR_PEDIDO("O valor do pedido deve ser maior que zero."),
  STATUS_PAGAMENTO("O status do pagamento é inválido.");

  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
