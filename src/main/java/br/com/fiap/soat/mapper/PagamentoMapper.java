package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.entity.StatusPagamento;
import java.time.LocalDateTime;

/**
 * Responsável por mapear um objeto NovoPagamentoDto para uma entidade JPA.
 */
public class PagamentoMapper {

  private PagamentoMapper() {}

  public static PagamentoJpa toEntity(CriarPagamentoDto novoPagamento) {

    var pagamento = new PagamentoJpa();
    pagamento.setCodigoPagamento(novoPagamento.getNumeroPedido());
    pagamento.setNumeroPedido(novoPagamento.getNumeroPedido());
    pagamento.setValor(novoPagamento.getValorPedido());
    pagamento.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
    pagamento.setTimestamp(LocalDateTime.now());
    return pagamento;
  }
}
