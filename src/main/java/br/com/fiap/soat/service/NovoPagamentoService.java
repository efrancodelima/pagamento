package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.mapper.PagamentoMapper;
import br.com.fiap.soat.repository.PagamentoRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.NovoPagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para criar um pagamento.
 */
@Component
public class NovoPagamentoService implements Service<CriarPagamentoDto, PagamentoJpa> {

  private final PagamentoRepository repository;

  @Autowired
  public NovoPagamentoService(PagamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public PagamentoJpa execute(CriarPagamentoDto novoPagamento)
      throws BadRequestException, BusinessRuleException {

    NovoPagamentoValidator.validar(novoPagamento);

    verificaSePedidoJaPossuiPagamento(novoPagamento.getNumeroPedido());
    
    var pagamento = PagamentoMapper.toEntity(novoPagamento);

    return repository.save(pagamento);
  }

  private void verificaSePedidoJaPossuiPagamento(Long numeroPedido) throws BusinessRuleException {

    var pedidoJaPossuiPagamento = repository.existsByNumeroPedido(numeroPedido);

    if (pedidoJaPossuiPagamento) {
      throw new BusinessRuleException();
    }
  }
}
