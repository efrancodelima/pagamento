package br.com.fiap.soat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade MongoDB Cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagamento")
public class PagamentoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long codigoPagamento;

  @Indexed
  private Long numeroPedido;

  private BigDecimal valor;

  private StatusPagamento status;

  private LocalDateTime timestamp;
}
