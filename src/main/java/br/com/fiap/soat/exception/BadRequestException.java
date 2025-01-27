package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Exceção customizada para requisições inválidas recebidas por este microsserviço.
 */
public class BadRequestException extends Exception {

  public BadRequestException(BadRequestMessage msg) {
    super(msg.getMessage());
  }
}
