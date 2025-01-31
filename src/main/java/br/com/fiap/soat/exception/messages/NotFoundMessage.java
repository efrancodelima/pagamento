package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  ID_PAGAMENTO("Nenhum pagamento foi encontrado para o id informado."),
  PAG_NUM_PEDIDO("Nenhum pagamento foi encontrado para o pedido informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
