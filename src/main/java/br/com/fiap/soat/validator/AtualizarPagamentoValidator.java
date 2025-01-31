package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar a requisição do controller AtualizarPagamento.
 */
public class AtualizarPagamentoValidator {

  private AtualizarPagamentoValidator() {}
  
  public static void validar(AtualizarPagamentoDto notificacaoPagamento)
      throws BadRequestException {

    if (notificacaoPagamento == null) {
      throw new BadRequestException(BadRequestMessage.PAG_NULL);
    }

    validarIdPagamento(notificacaoPagamento.getId());
    validarStatusPagamento(notificacaoPagamento.getStatus());
  }

  private static void validarIdPagamento(Long id) throws BadRequestException {
    
    if (id == null) {
      throw new BadRequestException(BadRequestMessage.ID_PAG_NULL);
    }
    
    if (id < 1) {
      throw new BadRequestException(BadRequestMessage.ID_PAG_MIN);
    }
  }

  private static void validarStatusPagamento(String status) throws BadRequestException {

    if (status == null || status.trim().isEmpty()) {
      throw new BadRequestException(BadRequestMessage.STT_PAG_NULL);
    }

    var statusEnum = StatusPagamento.fromString(status);
    if (statusEnum == null) {
      throw new BadRequestException(BadRequestMessage.STT_PAG_INV);
    }
  }
}
