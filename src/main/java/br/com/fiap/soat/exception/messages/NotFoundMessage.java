package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  ID_PAGAMENTO("Não foi encontrado nenhum pagamento para o id informado."),
  PAG_NUM_PEDIDO("Não foi encontrado nenhum pagamento para o pedido informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
