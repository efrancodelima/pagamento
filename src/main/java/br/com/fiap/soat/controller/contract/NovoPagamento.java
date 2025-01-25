package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.NovoPagamentoDto;
import br.com.fiap.soat.entity.PagamentoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Clientes, rota para buscar o cliente pelo CPF.
 */
@Tag(name = "Pagamento")
public interface NovoPagamento {

  /**
   * Endpoint para criar um novo pagamento.
   *
   * @param novoPagamento Um objeto contendo o número e o valor do pedido
   *      ao qual o pagamento será vinculado.
   * 
   * @return Um objeto do tipo ResponseEntity sem corpo (status 204),
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Criar novo pagamento", description = Constantes.DESCRICAO)
  
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_NO_CONTENT,
      description = Constantes.DESC_NO_CONTENT,
      content = @Content(mediaType = "application/json")),

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
  
  @PostMapping(value = "/novo/")

  ResponseEntity<ResponseWrapper<PagamentoJpa>>
        novoPagamento(@RequestBody NovoPagamentoDto novoPagamento);

  /** 
   * Constantes utilizadas pela interface CadastrarClienteApi.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Recebe um objeto contendo o número e o valor "
          + "de um pedido e cria um pagamento para ele.";
    
    public static final String CODE_NO_CONTENT = "204";
    public static final String DESC_NO_CONTENT = "No Content";

    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O CPF informado é inválido."
        }
        """;
    
    public static final String CODE_NOT_FOUND = "404";
    public static final String DESC_NOT_FOUND = "Not Found";
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "data": null,
          "errorMsg": "Cliente não encontrado."
        }
        """;
  }

}
