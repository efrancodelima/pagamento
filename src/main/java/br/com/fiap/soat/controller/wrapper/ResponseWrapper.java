package br.com.fiap.soat.controller.wrapper;

public class ResponseWrapper<T> {

  private T data;
  private String errorMsg;

  public ResponseWrapper(T data) {
    this.data = data;
  }

  public ResponseWrapper(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  // Getters e setters
  public T getData() {
    return data;
  }

  public String getErrorMsg() {
    return errorMsg;
  }
}
