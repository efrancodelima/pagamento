package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.math.BigDecimal;

/**
 * Responsável por validar a requisição do controller NovoPagamento.
 */
public class NovoPagamentoValidator {

  private NovoPagamentoValidator() {}

  /**
   * Valida a requisição do controller NovoPagamento.
   *
   * @param novoPag A requisição a ser validada.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   */
  public static void validar(NovoPagamentoDto novoPag) throws BadRequestException {

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
