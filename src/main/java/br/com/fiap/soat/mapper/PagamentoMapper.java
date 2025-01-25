package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.entity.StatusPagamento;

/**
 * Responsável por mapear um objeto NovoPagamentoDto para uma entidade JPA.
 */
public class PagamentoMapper {

  private PagamentoMapper() {}

  /**
   * Mapeia um objeto NovoPagamentoDto para uma entidade JPA.
   *
   * @param novoPagamento O objeto a ser mapeado.
   * @return A entidade JPA.
   */
  public static PagamentoJpa toEntity(NovoPagamentoDto novoPagamento) {

    var pagamento = new PagamentoJpa();
    pagamento.setCodigoPagamento(novoPagamento.getNumeroPedido().toString());
    pagamento.setNumeroPedido(novoPagamento.getNumeroPedido());
    pagamento.setValor(novoPagamento.getValorPedido());
    pagamento.setSituacao(StatusPagamento.AGUARDANDO_PAGAMENTO);
    return pagamento;
  }
    
}
