package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.NotificacaoMercadoPagoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
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
      throws BadRequestException, BusinessRuleException, NotFoundException {

    // Valida a requisição
    NotificacaoValidator.validar(notificacao);

    // Verifica se o pagamento existe
    var pagamentoOpt = repository.findById(notificacao.getId());
    if (!pagamentoOpt.isPresent()) {
      throw new NotFoundException(NotFoundMessage.ID_PAGAMENTO);
    }

    // Verifica se o pagamento já foi aprovado
    var pagamento = pagamentoOpt.get();
    if (pagamento.getStatus() == StatusPagamento.APROVADO) {
      throw new BusinessRuleException(BusinessRuleMessage.PEDIDO_PAGO);
    }

    // Atualiza o pagamento
    var status = StatusPagamento.fromString(notificacao.getStatus());
    pagamento.setStatus(status);

    pagamento.setTimestamp(LocalDateTime.now());
    
    repository.save(pagamento);
    
    return null;
  }

}
