package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Valida o número do pedido.
 */
public class NumeroPedidoValidator {

  private NumeroPedidoValidator() {}
  
  /**
   * Valida o número do pedido.
   *
   * @param numeroPedido O número do pedido a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(Long numeroPedido)
      throws BadRequestException {

    if (numeroPedido == null || numeroPedido < 1) {
      throw new BadRequestException(BadRequestMessage.NUMERO_PEDIDO);
    }
  }
}
