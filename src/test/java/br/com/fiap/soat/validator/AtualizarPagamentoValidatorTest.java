package br.com.fiap.soat.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import org.junit.jupiter.api.Test;

class AtualizarPagamentoValidatorTest {

  @Test
  void naoDeveLancarExcecao() {

    var notificacao = new AtualizarPagamentoDto(1L, StatusPagamento.APROVADO.getMessage());

    assertDoesNotThrow(() -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });
  }

  @Test
  void deveLancarExcecaoQuandoParametroForNulo() {

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(null);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoIdPagamentoForNulo() {

    var notificacao = new AtualizarPagamentoDto(null, StatusPagamento.APROVADO.getMessage());

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.ID_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoIdPagamentoForInvalido() {

    var notificacao = new AtualizarPagamentoDto(-1L, StatusPagamento.APROVADO.getMessage());

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.ID_PAG_MIN.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusPagamentoForNulo() {

    var notificacao = new AtualizarPagamentoDto(1L, null);

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusForStringVazia() {

    var notificacao = new AtualizarPagamentoDto(1L, "  ");

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusPagamentoForInvalido() {

    var notificacao = new AtualizarPagamentoDto(1L, "abc");

    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_INV.getMessage());
  }
}
