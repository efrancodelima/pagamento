package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.NotificacaoMercadoPagoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar a requisição do controller AtualizarPagamento.
 */
public class NotificacaoValidator {
  
  /**
   * Valida a requisição do controller AtualizarPagamento.
   *
   * @param notificacao O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(NotificacaoMercadoPagoDto notificacao) throws BadRequestException {

    if (notificacao.getId() == null || notificacao.getId() < 1) {
      throw new BadRequestException(BadRequestMessage.ID_PAGAMENTO);
    }
    
    if (notificacao.getStatus().trim().isEmpty()) {
      throw new BadRequestException(BadRequestMessage.STATUS_PAGAMENTO);
    }
    
  }
}
