package br.com.fiap.soat.entity;

/**
 * Lista os possíveis status do pagamento.
 * O valor de cada entrada é o texto usado pelo Mercado Pago nas notificações.
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
