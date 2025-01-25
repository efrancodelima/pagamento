package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PagamentoJpa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de clientes.
 */
@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoJpa, String> {}
