package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado para receber a notificação do sistema de pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPagamentoDto {

  @Schema(description = "Número do pedido].", example = "1")
  private Long numeroPedido;

  @Schema(description = "Valor do pedido].", example = "10")
  private BigDecimal valorPedido;
}
