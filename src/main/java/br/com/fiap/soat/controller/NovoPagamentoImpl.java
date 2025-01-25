package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.NovoPagamento;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.NovoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para criar um pagamento e vinculá-lo a um pedido.
 */
@RestController
@RequestMapping("/clientes")
public class NovoPagamentoImpl implements NovoPagamento {

  private final NovoPagamentoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para criar o pagamento.
   */
  @Autowired
  public NovoPagamentoImpl(NovoPagamentoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<PagamentoJpa>>
      novoPagamento(NovoPagamentoDto novoPagamento) {
    
    try {
      var pagamento = service.execute(novoPagamento);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(pagamento));
  
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
      
    }
  }
    
}
