package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.exception.messages.BadRequestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ConsultaInvalidaTest {

  private long request;
  private Response response;
  private final String url = "http://localhost:8081/pagamento/consultar/{pedido}";

  @Dado("que o usuário cria uma requisição para consultar o pagamento de um pedido")
  public void criarRequisicao() {
    request = -15L;
  }

  @Quando("o usuário envia a requisição para o endpoint de consulta")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .get(url, request);
  }

  @Entao("o usuário deve receber uma resposta com status code 400")
  public void conferirStatusCode() {
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
  }

  @E("a resposta deve conter o motivo do erro")
  public void conferirMensagemErro() throws Exception {
    
    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());
    var msgErroRecebida = responseBody.get("errorMsg").asText();
    
    assertEquals(BadRequestMessage.NUM_PED_MIN.getMessage(), msgErroRecebida);
  }
}
