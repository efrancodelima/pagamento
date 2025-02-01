package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pagamento")
public interface CriarPagamento {

  @Hidden
  @PostMapping(value = "/criar/")
  ResponseEntity<ResponseWrapper<PagamentoJpa>>
        criarPagamento(@RequestBody CriarPagamentoDto novoPagamento);

}
