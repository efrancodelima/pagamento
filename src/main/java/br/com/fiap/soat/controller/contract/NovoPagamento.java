package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Pagamentos, rota para criar novo pagamento.
 */
@Tag(name = "Pagamento")
public interface NovoPagamento {

  /**
   * Endpoint para criar um novo pagamento.
   * Webhook para receber a notificação do sistema de pedidos.
   * Esse endpoint não deve ser exposto publicamente na API do Swagger.
   *
   * @param novoPagamento Um objeto contendo o número e o valor do pedido
   *      ao qual o pagamento será vinculado.
   * 
   * @return Um objeto do tipo ResponseEntity sem corpo (status 204),
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Hidden
  @PostMapping(value = "/novo/")
  ResponseEntity<ResponseWrapper<PagamentoJpa>>
        novoPagamento(@RequestBody NovoPagamentoDto novoPagamento);


}
