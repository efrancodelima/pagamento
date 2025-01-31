package br.com.fiap.soat.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import org.junit.jupiter.api.Test;

class NumeroPedidoValidatorTest {
  

  @Test
  void naoDeveLancarExcecao() {
    assertDoesNotThrow(() -> {
      NumeroPedidoValidator.validar(1L);
    });
  }

  @Test
  void deveLancarExcecaoSeNumeroPedidoForNulo() {
    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(null);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoSeNumeroPedidoForMenorQueUm() {
    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(0L);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_MIN.getMessage());
  }
}
