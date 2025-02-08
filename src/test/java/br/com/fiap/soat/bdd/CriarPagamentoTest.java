package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.dto.CriarPagamentoDto;
import br.com.fiap.soat.entity.StatusPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import java.math.BigDecimal;
import org.springframework.http.MediaType;

public class CriarPagamentoTest {

  private CriarPagamentoDto request;
  private Response response;
  private final String url = "http://localhost:8081/pagamento/criar/";

  @Dado("que o sistema de pedido tem uma notificação de checkout para fazer")
  public void criarNotificacao() {
    request = new CriarPagamentoDto(1L, BigDecimal.valueOf(45));
  }

  @Quando("o sistema de pagamento receber a notificação")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(request)
      .post(url);
  }

  @Entao("deve retornar o status aguardando pagamento")
  public void conferirMensagemErro() throws Exception {

    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());
    var numeroPedidoRecebido = responseBody.get("data").get("numeroPedido").asLong();
    var statusPedidoRecebido = responseBody.get("data").get("status").asText();

    assertEquals(request.getNumeroPedido(), numeroPedidoRecebido);
    assertEquals(StatusPagamento.AGUARDANDO_PAGAMENTO.toString(), statusPedidoRecebido);
  }
}
