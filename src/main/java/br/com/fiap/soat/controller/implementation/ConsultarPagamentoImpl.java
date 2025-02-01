package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.ConsultarPagamento;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.ConsultarPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class ConsultarPagamentoImpl  implements ConsultarPagamento {

  private final ConsultarPagamentoService service;

  @Autowired
  public ConsultarPagamentoImpl(ConsultarPagamentoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<PagamentoJpa>>
      consultarPagamento(long numeroPedido) {

    try {
      var pagamento = service.execute(numeroPedido);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(pagamento));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));

    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}
