package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
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
public class NovoPagamentoService implements Service<NovoPagamentoDto, PagamentoJpa> {

  private final PagamentoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public NovoPagamentoService(PagamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public PagamentoJpa execute(NovoPagamentoDto novoPagamento)
      throws BadRequestException, BusinessRuleException {

    // Valida a requisição
    NovoPagamentoValidator.validar(novoPagamento);

    // Verifica se o pedido já possui um pagamento vinculado
    var pedidoJaPossuiPagamento = repository
        .existsByNumeroPedido(novoPagamento.getNumeroPedido());

    if (pedidoJaPossuiPagamento) {
      throw new BusinessRuleException(BusinessRuleMessage.PAGAMENTO_JA_EXISTE);
    }
    
    // Mapeia o novo pagamento e salva
    var pagamento = PagamentoMapper.toEntity(novoPagamento);
    return repository.save(pagamento);
  }

}
