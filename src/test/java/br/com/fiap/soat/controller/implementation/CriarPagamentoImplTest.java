package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.service.CriarPagamentoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class CriarPagamentoImplTest {

  AutoCloseable closeable;
  CriarPagamentoImpl controller;
  
  @Mock
  CriarPagamentoService serviceMock;

  @Mock
  CriarPagamentoDto requisicaoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.controller = new CriarPagamentoImpl(serviceMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCriarUmPagamentoComSucesso() throws Exception {
    var pagamentoJpa = new PagamentoJpa();
    doReturn(pagamentoJpa).when(serviceMock).execute(Mockito.any());
    
    assertDoesNotThrow(() -> {
      
      var response = controller.criarPagamento(requisicaoMock);
      
      assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
      assertEquals(pagamentoJpa, response.getBody().getData());
      assertEquals(null, response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveRetornarUmBadRequest() throws Exception {

    var exception = new BadRequestException(BadRequestMessage.PAG_NULL);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {
      
      var response = controller.criarPagamento(requisicaoMock);
      
      assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveRetornarUmBusinesRule() throws Exception {

    var exception = new BusinessRuleException();
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {
      
      var response = controller.criarPagamento(requisicaoMock);
      
      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
    });
  }
}
