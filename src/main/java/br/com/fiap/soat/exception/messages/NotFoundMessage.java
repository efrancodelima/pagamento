package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  PAGAMENTO("Não foi encontrado nenhum pagamento para o número informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
