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

    // Arrange
    var notificacao = new AtualizarPagamentoDto(1L, StatusPagamento.APROVADO.getMessage());

    // Act and assert
    assertDoesNotThrow(() -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });
  }

  @Test
  void deveLancarExcecaoQuandoParametroForNulo() {

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(null);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoIdPagamentoForNulo() {

    // Arrange
    var notificacao = new AtualizarPagamentoDto(null, StatusPagamento.APROVADO.getMessage());

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.ID_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoIdPagamentoForInvalido() {

    // Arrange
    var notificacao = new AtualizarPagamentoDto(-1L, StatusPagamento.APROVADO.getMessage());

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.ID_PAG_MIN.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusPagamentoForNulo() {

    // Arrange
    var notificacao = new AtualizarPagamentoDto(1L, null);

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusForStringVazia() {

    // Arrange
    var notificacao = new AtualizarPagamentoDto(1L, "  ");

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoStatusPagamentoForInvalido() {

    // Arrange
    var notificacao = new AtualizarPagamentoDto(1L, "abc");

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      AtualizarPagamentoValidator.validar(notificacao);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.STT_PAG_INV.getMessage());
  }
}
