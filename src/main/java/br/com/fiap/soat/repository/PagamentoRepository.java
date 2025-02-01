package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PagamentoJpa;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoJpa, Long> {

  Optional<PagamentoJpa> findByNumeroPedido(long numeroPedido);

  boolean existsByNumeroPedido(long numeroPedido);
}
