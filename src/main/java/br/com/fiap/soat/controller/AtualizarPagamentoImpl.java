package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.AtualizarPagamento;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.NotificacaoMercadoPagoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.AtualizarPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para atualizar um pagamento.
 * Responsável por receber as notificações do Mercado Pago.
 */
@RestController
@RequestMapping("/pagamento")
public class AtualizarPagamentoImpl implements AtualizarPagamento {

  private final AtualizarPagamentoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para criar o pagamento.
   */
  @Autowired
  public AtualizarPagamentoImpl(AtualizarPagamentoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<Void>>
      atualizarPagamento(NotificacaoMercadoPagoDto notificacao) {
    
    try {
      service.execute(notificacao);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
    
}
