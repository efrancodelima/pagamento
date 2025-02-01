package br.com.fiap.soat.service.contract;

import br.com.fiap.soat.exception.AppException;

public interface Service<P, Q> {
  Q execute(P requisicao) throws AppException;
}

