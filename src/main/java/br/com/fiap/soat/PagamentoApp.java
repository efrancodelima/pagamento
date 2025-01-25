package br.com.fiap.soat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Classe responsável por iniciar o microsserviço.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.com.fiap.soat.repository")
public class PagamentoApp {

  /**
   * Método responsável por iniciar o microsserviço.
   */
  public static void main(String[] args) {
    var application = new SpringApplication(PagamentoApp.class);
    application.setApplicationStartup(new BufferingApplicationStartup(1024));
    application.run(args);
  }  
}
