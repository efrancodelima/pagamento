package br.com.fiap.soat.service;

import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.PagamentoRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.NumeroPedidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultarPagamentoService implements Service<Long, PagamentoJpa> {

  private final PagamentoRepository repository;

  @Autowired
  public ConsultarPagamentoService(PagamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public PagamentoJpa execute(Long numeroPedido) throws BadRequestException, NotFoundException {

    NumeroPedidoValidator.validar(numeroPedido);

    var pagamento =  repository.findByNumeroPedido(numeroPedido);

    if (!pagamento.isPresent()) {
      throw new NotFoundException(NotFoundMessage.PAG_NUM_PEDIDO);
    }

    return pagamento.get();
  }
}
