package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.NotFoundMessage;

public class NotFoundException extends AppException {

  public NotFoundException(NotFoundMessage msg) {
    super(msg.getMessage());
  }
}
