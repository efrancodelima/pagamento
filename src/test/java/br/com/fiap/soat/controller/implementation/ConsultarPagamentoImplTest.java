package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.entity.PagamentoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.ConsultarPagamentoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ConsultarPagamentoImplTest {

  AutoCloseable closeable;
  ConsultarPagamentoImpl controller;
  
  @Mock
  ConsultarPagamentoService serviceMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.controller = new ConsultarPagamentoImpl(serviceMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarUmPagamentoComSucesso() throws Exception {
    var pagamentoJpa = new PagamentoJpa();
    doReturn(pagamentoJpa).when(serviceMock).execute(Mockito.anyLong());
    
    assertDoesNotThrow(() -> {
      
      var response = controller.consultarPagamento(1L);
      
      assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
      assertEquals(pagamentoJpa, response.getBody().getData());
      assertEquals(null, response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveRetornarUmBadRequest() throws Exception {

    var exception = new BadRequestException(BadRequestMessage.NUM_PED_NULL);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {
      
      var response = controller.consultarPagamento(1L);
      
      assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveRetornarUmNotFound() throws Exception {

    var exception = new NotFoundException(NotFoundMessage.PAG_NUM_PEDIDO);
    doThrow(exception).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {

      var response = controller.consultarPagamento(1L);
      
      assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(exception.getMessage(), response.getBody().getErrorMsg());
    });
  }
}
