package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  PAG_NULL("Informe os dados do pagamento."),
  NUM_PED_NULL("Informe o número do pedido."),
  NUM_PED_MIN("O número do pedido é inválido."),
  VAL_PED_NULL("O valor do pedido deve ser maior que zero."),
  VAL_PED_MIN("O valor do pedido deve ser maior que zero."),
  ID_PAG_NULL("Informe o id do pagamento."),
  ID_PAG_MIN("O id do pagamento deve ser maior que zero."),
  STT_PAG_NULL("Informe o status do pagamento."),
  STT_PAG_INV("O status do pagamento é inválido.");

  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
