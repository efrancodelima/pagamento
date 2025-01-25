package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PagamentoJpa;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de clientes.
 */
@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoJpa, String> {

  /**
   * Busca um pagamento a partir do número do pedido.
   *
   * @param numeroPedido O número do pedido.
   * @return Um objeto contendo o pagamento entrado ou null.
   */
  Optional<PagamentoJpa> findByNumeroPedido(Long numeroPedido);
}
