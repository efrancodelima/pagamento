package br.com.fiap.soat.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.repository.PagamentoRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ConsultarPagamentoServiceTest {

  AutoCloseable closeable;

  @Mock
  PagamentoRepository repositoryMock;

  @Mock
  PagamentoJpa pagamentoJpaMock;

  @InjectMocks
  ConsultarPagamentoService service;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarPagamentoComSucesso() {

    doReturn(Optional.of(pagamentoJpaMock))
        .when(repositoryMock).findByNumeroPedido(Mockito.anyLong());

    assertDoesNotThrow(() -> {
      var response = service.execute(1L);
      assertEquals(pagamentoJpaMock, response);
    });
  }
}
