package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pagamento")
public interface AtualizarPagamento {

  @Hidden
  @PatchMapping(value = "/atualizar/")
  ResponseEntity<ResponseWrapper<Void>>
      atualizarPagamento(@RequestBody AtualizarPagamentoDto notificacao);

}
