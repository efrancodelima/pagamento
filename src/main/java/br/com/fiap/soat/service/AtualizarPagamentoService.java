package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.PagamentoRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.AtualizarPagamentoValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizarPagamentoService implements Service<AtualizarPagamentoDto, Void> {

  private final PagamentoRepository repository;

  @Autowired
  public AtualizarPagamentoService(PagamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public Void execute(AtualizarPagamentoDto notificacaoPagamento)
      throws BadRequestException, NotFoundException {

    AtualizarPagamentoValidator.validar(notificacaoPagamento);

    var pagamento = buscarPagamento(notificacaoPagamento.getId());
    
    var status = StatusPagamento.fromString(notificacaoPagamento.getStatus());
    pagamento.setStatus(status);
    pagamento.setTimestamp(LocalDateTime.now());
    
    repository.save(pagamento);
    return null;
  }

  private PagamentoJpa buscarPagamento(Long codigoPagamento) throws NotFoundException {

    var pagamentoOpt = repository.findById(codigoPagamento);

    if (!pagamentoOpt.isPresent()) {
      throw new NotFoundException(NotFoundMessage.ID_PAGAMENTO);
    }
    return pagamentoOpt.get();
  }
}
