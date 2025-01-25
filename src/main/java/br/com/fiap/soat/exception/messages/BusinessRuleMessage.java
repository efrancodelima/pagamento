package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções de regra de negócio.
 */
public enum BusinessRuleMessage {
    
  PAGAMENTO_JA_EXISTE("Esse pedido já possui um pagamento vinculado.");

  private String mensagem;

  BusinessRuleMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
