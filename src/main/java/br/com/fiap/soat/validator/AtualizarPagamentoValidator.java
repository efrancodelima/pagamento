package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar a requisição do controller AtualizarPagamento.
 */
public class AtualizarPagamentoValidator {

  private AtualizarPagamentoValidator() {}
  
  public static void validar(AtualizarPagamentoDto notificacaoPagamento)
      throws BadRequestException {

    if (notificacaoPagamento.getId() == null || notificacaoPagamento.getId() < 1) {
      throw new BadRequestException(BadRequestMessage.ID_PAGAMENTO);
    }
    
    if (notificacaoPagamento.getStatus().trim().isEmpty()) {
      throw new BadRequestException(BadRequestMessage.STATUS_PAGAMENTO);
    }
  }
}
