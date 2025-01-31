package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.CriarPagamento;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.service.CriarPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para criar um pagamento e vinculá-lo a um pedido.
 * Webhook para receber as notificações do sistema de pedido.
 */
@RestController
@RequestMapping("/pagamento")
public class CriarPagamentoImpl implements CriarPagamento {

  private final CriarPagamentoService service;

  @Autowired
  public CriarPagamentoImpl(CriarPagamentoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<PagamentoJpa>>
      criarPagamento(CriarPagamentoDto novoPagamento) {
    
    try {
      var pagamento = service.execute(novoPagamento);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(pagamento));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BusinessRuleException e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
    
}
