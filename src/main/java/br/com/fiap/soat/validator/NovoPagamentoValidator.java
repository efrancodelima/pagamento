package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.math.BigDecimal;

/**
 * Responsável por validar a requisição do controller NovoPagamento.
 */
public class NovoPagamentoValidator {

  private NovoPagamentoValidator() {}

  public static void validar(CriarPagamentoDto novoPag) throws BadRequestException {

    NumeroPedidoValidator.validar(novoPag.getNumeroPedido());
    validarValorPedido(novoPag.getValorPedido());
  }

  private static void validarValorPedido(BigDecimal valorPedido)
      throws BadRequestException {

    if (valorPedido == null || valorPedido.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BadRequestException(BadRequestMessage.VALOR_PEDIDO);
    }
  }
}
