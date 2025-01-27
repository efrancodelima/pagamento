package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado para receber a notificação do Mercado Pago.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPagamentoDto {

  @Schema(description = "Código de identificação do pagamento.", example = "1")
  public Long id;

  @Schema(description = "Situação do pagamento.", example = "PENDING")
  public String status;

}
