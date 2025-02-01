package br.com.fiap.soat.mapper;

import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PagamentoMapperTest {

  @Test
  void deveMapearUmDtoComSucesso() {

    // Arrange
    var pagamentoDto = new CriarPagamentoDto(1L, BigDecimal.valueOf(25));

    // Act
    var pagamentoJpa = PagamentoMapper.toEntity(pagamentoDto);

    // Assert
    assertEquals(pagamentoDto.getNumeroPedido(), pagamentoJpa.getNumeroPedido());
    assertEquals(pagamentoDto.getValorPedido(), pagamentoJpa.getValor());
    assertEquals(StatusPagamento.AGUARDANDO_PAGAMENTO, pagamentoJpa.getStatus());
    assertEquals(false, pagamentoJpa.getTimestamp() == null);
  }
}
