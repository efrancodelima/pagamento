package br.com.fiap.soat.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CriarPagamentoValidatorTest {

  @Test
  void naoDeveLancarExcecao() {

    // Arrange
    var pagamento = new CriarPagamentoDto(1L, BigDecimal.valueOf(10));

    // Act and assert
    assertDoesNotThrow(() -> {
      CriarPagamentoValidator.validar(pagamento);
    });
  }

  @Test
  void deveLancarExcecaoQuandoParametroForNulo() {
    var exception = assertThrows(BadRequestException.class, () -> {
      CriarPagamentoValidator.validar(null);
    });
    
    assertEquals(exception.getMessage(), BadRequestMessage.PAG_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoValorForNulo() {

    // Arrange
    var pagamento = new CriarPagamentoDto(1L, null);

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      CriarPagamentoValidator.validar(pagamento);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.VAL_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoValorForMenorQueUmCentavo() {

    // Arrange
    var pagamento = new CriarPagamentoDto(1L, BigDecimal.ZERO);

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      CriarPagamentoValidator.validar(pagamento);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.VAL_PED_MIN.getMessage());
  }
}
