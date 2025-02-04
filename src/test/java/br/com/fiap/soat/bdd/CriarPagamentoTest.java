package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.http.MediaType;

public class CriarPagamentoTest {

  private CriarPagamentoDto requisicao;
  private Response resposta;
  private final String url = "http://localhost:8081/pagamento/criar/";

  @Given("que o sistema de pedido tem uma notificação de checkout para fazer")
  public void criarNotificacao() {
    requisicao = new CriarPagamentoDto(8008L, BigDecimal.valueOf(45));
  }

  @When("o sistema de pagamento receber a notificação")
  public void enviarRequisicao() {
    resposta = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(requisicao)
      .post(url);
  }

  @Then("deve retornar o status aguardando pagamento")
  public void conferirMensagemErro() throws Exception {

    var data = getDadosResposta();

    assertEquals(requisicao.getNumeroPedido(), Long.valueOf((Integer) data.get("numeroPedido")));
    assertEquals(StatusPagamento.AGUARDANDO_PAGAMENTO.toString(), data.get("status"));
  }

  // Métodos auxiliares
  private Map<String, Object> getDadosResposta() throws Exception {
    
    ObjectMapper mapper = new ObjectMapper();
    
    Map<String, Object> responseMap = 
        mapper.readValue(resposta.getBody().asString(),
          new TypeReference<Map<String, Object>>() {});
    
    return (Map<String, Object>) responseMap.get("data");
  }
}
