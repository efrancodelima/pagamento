package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.dto.AtualizarPagamentoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.AtualizarPagamentoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class AtualizarPagamentoImplTest {

  AutoCloseable closeable;

  @Mock
  AtualizarPagamentoService serviceMock;

  @Mock
  AtualizarPagamentoDto requisicaoMock;

  @InjectMocks
  AtualizarPagamentoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAtualizarUmPagamentoComSucesso() throws Exception {

    // Arrange
    doNothing().when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.atualizarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var exception = new BadRequestException(BadRequestMessage.PAG_NULL);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.atualizarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusNotFound() throws Exception {

    // Arrange
    var exception = new NotFoundException(NotFoundMessage.ID_PAGAMENTO);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.atualizarPagamento(requisicaoMock);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
  }
}
