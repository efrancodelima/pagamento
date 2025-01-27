package br.com.fiap.soat.exception;

/**
 * Exceção customizada para regras de negócio.
 */
public class BusinessRuleException extends Exception {

  public BusinessRuleException() {
    super("Esse pedido já possui um pagamento vinculado.");
  }
}
