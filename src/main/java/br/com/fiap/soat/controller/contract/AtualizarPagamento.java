package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.NotificacaoMercadoPagoDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Pagamentos, rota para criar novo pagamento.
 */
@Tag(name = "Pagamento")
public interface AtualizarPagamento {

  /**
   * Endpoint para atualizar um pagamento.
   * Webhook para receber a notificação do Mercado Pago.
   * Esse endpoint não deve ser exposto publicamente na API do Swagger.
   *
   * @param notificacao .
   * 
   * @return Um objeto do tipo ResponseEntity sem corpo (status 204),
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Hidden
  @PostMapping(value = "/atualizar/")
  ResponseEntity<ResponseWrapper<Void>>
      atualizarPagamento(@RequestBody NotificacaoMercadoPagoDto notificacao);

}
