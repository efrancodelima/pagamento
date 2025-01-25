package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.NotificacaoMercadoPagoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.PagamentoRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.NotificacaoValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para atuializar um pagamento.
 */
@Component
public class AtualizarPagamentoService implements Service<NotificacaoMercadoPagoDto, Void> {

  private final PagamentoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public AtualizarPagamentoService(PagamentoRepository repository) {
    this.repository = repository;
  }

  @Override
  public Void execute(NotificacaoMercadoPagoDto notificacao)
      throws BadRequestException, NotFoundException {

    // Valida a requisição
    NotificacaoValidator.validar(notificacao);

    // Verifica se o pagamento existe
    var pagamentoOpt = repository.findById(notificacao.getId());
    if (!pagamentoOpt.isPresent()) {
      throw new NotFoundException(NotFoundMessage.ID_PAGAMENTO);
    }

    // Atualiza o pagamento
    var pagamento = pagamentoOpt.get();
    var status = StatusPagamento.fromString(notificacao.getStatus());
    pagamento.setStatus(status);

    pagamento.setTimestamp(LocalDateTime.now());
    
    repository.save(pagamento);
    
    return null;
  }

}
