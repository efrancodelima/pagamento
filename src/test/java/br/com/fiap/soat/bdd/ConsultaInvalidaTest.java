package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.exception.messages.BadRequestMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ConsultaInvalidaTest {

  private long request;
  private Response response;
  private final String url = "http://localhost:8081/pagamento/consultar/{pedido}";

  @Given("que o usuário cria uma requisição para consultar o pagamento de um pedido")
  public void criarRequisicao() {
    request = -15L;
  }

  @When("o usuário envia a requisição para o endpoint de consulta")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .get(url, request);
  }

  @Then("o usuário deve receber uma resposta com status code 400")
  public void conferirStatusCode() {
    
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
  }

  @Then("a resposta deve conter o motivo do erro")
  public void conferirMensagemErro()
      throws Exception {
    
    assertEquals(BadRequestMessage.NUM_PED_MIN.getMessage(), getErrorMessage(response));
  }

  // Métodos auxiliares
  private String getErrorMessage(Response response) throws Exception {
    
    ObjectMapper mapper = new ObjectMapper();
    
    Map<String, Object> responseMap = 
        mapper.readValue(response.getBody().asString(),
          new TypeReference<Map<String, Object>>() {});
    
    return (String) responseMap.get("errorMsg");
  }
}
