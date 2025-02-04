package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.repository.PagamentoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CriarPagamentoTest {

  @Autowired
  PagamentoRepository repo;

  private CriarPagamentoDto request;
  private Response response;
  private final String url = "http://localhost:8081/pagamento/criar/";

  @Given("que o sistema de pedido tem uma notificação de checkout para fazer")
  public void criarNotificacao() {
    request = new CriarPagamentoDto(8L, BigDecimal.valueOf(45));
  }

  @When("o sistema de pagamento receber a notificação")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .post(url, request);
  }

  @Then("deve criar um pagamento para o pedido")
  public void conferirStatusCode() {
    
    var pagamento = repo.findByNumeroPedido(request.getNumeroPedido());

    assertEquals(true, pagamento.isPresent());
    assertEquals(request.getValorPedido(), pagamento.get().getValor());
  }

  @Then("retornar o status aguardando pagamento")
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
