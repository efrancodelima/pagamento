package br.com.fiap.soat.entity;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Lista os possíveios status do pagamento.
 */
public enum StatusPagamento {

  AGUARDANDO_PAGAMENTO, APROVADO, REPROVADO;

  /**
   * Retorna o enum correspondente a partir de uma string.
   *
   * @param statusPagamentoStr Status do pagamento (string).
   * @return Status do pagamento (enum).
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   */
  public static StatusPagamento fromString(String statusPagamentoStr) throws BadRequestException {

    statusPagamentoStr = statusPagamentoStr == null
      ? null : statusPagamentoStr.toUpperCase().trim();

    try {
      return StatusPagamento.valueOf(statusPagamentoStr);

    } catch (Exception e) {
      throw new BadRequestException(BadRequestMessage.STATUS_PAGAMENTO);
    }
  }
    
}
