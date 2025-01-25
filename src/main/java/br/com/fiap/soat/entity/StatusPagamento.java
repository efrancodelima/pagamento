package br.com.fiap.soat.entity;

/**
 * Lista os possíveios status do pagamento.
 */
public enum StatusPagamento {

  AGUARDANDO_PAGAMENTO("PENDING"),
  APROVADO("APPROVED"),
  REPROVADO("REJECTED"),
  DEVOLVIDO("REFUNDED"),
  CANCELADO("CHARGED_BACK"),
  EM_DISPUTA("IN_DISPUTE");

  private String texto;

  StatusPagamento(String texto) {
    this.texto = texto;
  }

  public String getMessage() {
    return texto;
  }

  /**
   * Retorna o enum cujo texto corresponde ao texto recebido.
   *
   * @param texto O status do pagamento (string).
   * @return O status do pagamento (enum).
   */
  public static StatusPagamento fromString(String texto) {
    
    for (var itemEnum : StatusPagamento.values()) {
      texto = texto.trim().toUpperCase();

      if (itemEnum.texto.equals(texto)) {
        return itemEnum;
      }
    }
    return null;
  }
  
}
