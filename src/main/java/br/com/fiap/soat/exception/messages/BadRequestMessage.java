package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  NUM_PED_NULL("Informe o número do pedido."),
  NUM_PED_MIN("O número do pedido é inválido."),
  VALOR_PEDIDO("O valor do pedido deve ser maior que zero."),
  ID_PAGAMENTO("O id do pagamento é inválido."),
  STATUS_PAGAMENTO("O status do pagamento é inválido.");

  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
