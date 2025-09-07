package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class NotificationService extends ExternalServiceWebClient{


  public Mono<String> getNotificationResponse() {
    return webClient.post()
        .uri("v2/authorize")
        .retrieve()
        .bodyToMono(String.class)
        .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));
  }
}
