package com.xpcosmos.transacoes_bancarias.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .baseUrl("https://util.devi.tools/api/")
        .build();
  }
}
