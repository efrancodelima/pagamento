package br.com.fiap.soat.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.entity.StatusPagamento;
import br.com.fiap.soat.repository.PagamentoRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AtualizarPagamentoServiceTest {

  AutoCloseable closeable;
  AtualizarPagamentoService service;
  
  @Mock
  PagamentoRepository repositoryMock;

  @Mock
  PagamentoJpa pagamentoJpaMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.service = new AtualizarPagamentoService(repositoryMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAtualizarPagamentoComSucesso() {

    doReturn(Optional.of(pagamentoJpaMock)).when(repositoryMock).findById(Mockito.anyLong());

    var requisicao = new AtualizarPagamentoDto(1L, StatusPagamento.APROVADO.getMessage());

    assertDoesNotThrow(() -> {
      var response = service.execute(requisicao);
      assertEquals(null, response);
      // esse service atende a um webhook, então a resposta deve ser nula
    });
  }
}
