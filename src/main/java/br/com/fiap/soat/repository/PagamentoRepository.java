package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PagamentoJpa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Interface do repositório de clientes.
 */
@Component
public interface PagamentoRepository extends MongoRepository<PagamentoJpa, String> {}
