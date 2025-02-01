package br.com.fiap.soat.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.repository.PagamentoRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CriarPagamentoServiceTest {

  AutoCloseable closeable;

  @Mock
  PagamentoRepository repositoryMock;

  @Mock
  PagamentoJpa pagamentoJpaMock;

  @InjectMocks
  CriarPagamentoService service;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCriarPagamentoComSucesso() {

    doReturn(pagamentoJpaMock).when(repositoryMock).save(Mockito.any());

    var requisicao = new CriarPagamentoDto(1L, BigDecimal.valueOf(10));

    assertDoesNotThrow(() -> {
      var response = service.execute(requisicao);
      assertEquals(pagamentoJpaMock, response);
    });
  }
}
