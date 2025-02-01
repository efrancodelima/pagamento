package br.com.fiap.soat.controller.implementation;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class CriarPagamentoImplTest {

  AutoCloseable closeable;

  @Mock
  CriarPagamentoService serviceMock;

  @Mock
  CriarPagamentoDto requisicaoMock;

  @InjectMocks
  CriarPagamentoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCriarPagamentoComSucesso() throws Exception {

    // Arrange
    var pagamentoJpa = new PagamentoJpa();
    doReturn(pagamentoJpa).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.criarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    assertEquals(pagamentoJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var exception = new BadRequestException(BadRequestMessage.PAG_NULL);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.criarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBusinesRule() throws Exception {

    // Arrange
    var exception = new BusinessRuleException();
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.criarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
  }
}
