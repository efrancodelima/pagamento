package br.com.fiap.soat.service.contract;

/**
 * Interface para os services.
 */
public interface Service<P, Q> {
  Q execute(P requisicao) throws Exception;
}

