package br.com.fiap.soat.controller.contract;


import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.PagamentoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface da API Pagamentos, rota para consultar pagamento.
 */
@Tag(name = "Pagamento")
public interface ConsultarPagamento {

  @Operation(summary = "Consultar pagamento do pedido", description = Constantes.DESCRICAO)

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK))),
    
    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST))),
    
    @ApiResponse(
      responseCode = Constantes.CODE_NOT_FOUND,
      description = Constantes.DESC_NOT_FOUND,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_NOT_FOUND)))
  })

  @GetMapping(value = "/consultar/{pedido}")

  @Parameter(name = "pedido", description = "O número do pedido", required = true)

  ResponseEntity<ResponseWrapper<PagamentoJpa>>
      consultarPagamento(@PathVariable("pedido") long numeroPedido);

  /** 
   * Constantes usadas na interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Informe o número do pedido "
        + "para consultar o status do pagamento";
    
    public static final String CODE_OK = "200";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_NOT_FOUND = "404";

    public static final String DESC_OK = "Success";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String DESC_NOT_FOUND = "Not Found";
    
    public static final String EXAMPLE_OK = """
        {
          "data": {
            "codigoPagamento": "10",
            "numeroPedido": 10,
            "valor": 50,
            "situacao": "AGUARDANDO_PAGAMENTO",
            "timestamp": "2025-01-20T18:00:0.000000000"
          },
          "errorMsg": null
        }
        """;

    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O número do pedido é inválido."
        }
        """;
    
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "data": null,
          "errorMsg": "Não foi encontrado nenhum pedido para o número informado."
        }
        """;


  }

}
