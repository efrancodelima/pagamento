package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.NotFoundMessage;

/**
 * Exceção customizada para recursos não encontrados na base de dados.
 */
public class NotFoundException extends Exception {

  public NotFoundException(NotFoundMessage msg) {
    super(msg.getMessage());
  }
}
