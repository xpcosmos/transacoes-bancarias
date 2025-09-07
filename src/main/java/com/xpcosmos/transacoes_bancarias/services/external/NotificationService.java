package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class NotificationService extends ExternalService{


  public Mono<String> getNotificationResponse(){
    return webClient.post()
      .uri(getUri())
      .retrieve()
      .bodyToMono(String.class)
      .retryWhen(
        Retry.fixedDelay(3, Duration.ofSeconds(3))
        );
  }

  @Override
  public String getUri() {
    return "https://util.devi.tools/api/v1/notify";
  }
}
