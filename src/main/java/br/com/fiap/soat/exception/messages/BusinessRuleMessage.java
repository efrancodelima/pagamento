package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções de regra de negócio.
 */
public enum BusinessRuleMessage {
    
  PEDIDO_PAGO("Esse pedido já teve o pagamento aprovado.");

  private String mensagem;

  BusinessRuleMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
