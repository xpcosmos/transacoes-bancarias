package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

public class ExternalServiceWebClient {
  WebClient webClient = WebClient.builder()
      .baseUrl("https://util.devi.tools/api/")
      .clientConnector(new ReactorClientHttpConnector(
          HttpClient.create().responseTimeout(Duration.ofSeconds(15L))))
      .build();
}
