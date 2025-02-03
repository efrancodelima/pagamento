package br.com.fiap.soat.bdd;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class InvalidRequestTest {

  private long request;
  private Response response;
  private final String ENDPOINT_CONSULTAR_PAGAMENTO = "http://localhost:8081/pagamento/consultar/{pedido}";

  @Given("que o usuário cria uma requisição para consultar o pagamento de um pedido")
  public void que_o_usuário_cria_uma_requisição_para_consultar_o_pagamento_de_um_pedido() {
    request = -15L;
  }

  @When("o usuário envia a requisição para o endpoint de consulta")
  public void o_usuário_envia_a_requisição_para_o_endpoint_de_consulta() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .get(ENDPOINT_CONSULTAR_PAGAMENTO, request);
  }

  @Then("o usuário deve receber uma resposta com status code 400")
  public void o_usuário_deve_receber_uma_resposta_com_status_code_400() {
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
  }
  
}
