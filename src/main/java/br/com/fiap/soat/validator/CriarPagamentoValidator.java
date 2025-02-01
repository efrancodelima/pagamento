package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.math.BigDecimal;

public class CriarPagamentoValidator {

  private CriarPagamentoValidator() {}

  public static void validar(CriarPagamentoDto pagamento) throws BadRequestException {

    if (pagamento == null) {
      throw new BadRequestException(BadRequestMessage.PAG_NULL);
    }

    NumeroPedidoValidator.validar(pagamento.getNumeroPedido());
    validarValorPedido(pagamento.getValorPedido());
  }

  private static void validarValorPedido(BigDecimal valorPedido)
      throws BadRequestException {

    if (valorPedido == null) {
      throw new BadRequestException(BadRequestMessage.VAL_PED_NULL);
    }
        
    if (valorPedido.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BadRequestException(BadRequestMessage.VAL_PED_MIN);
    }
  }
}
