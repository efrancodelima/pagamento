package br.com.fiap.soat.exception;

public class BusinessRuleException extends AppException {

  public BusinessRuleException() {
    super("Esse pedido já possui um pagamento vinculado.");
    // nesse microsserviço só tem essa mensagem de regra de negócio
  }
}
