package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class AuthorizationService extends ExternalServiceWebClient {

  public Mono<AuthorizationDTO> getAuthorizationResponse() throws Forbidden {

    return webClient.get().uri("v2/authorize").retrieve().bodyToMono(AuthorizationDTO.class)
        .retryWhen(
          Retry.fixedDelay(3l, Duration.ofSeconds(15))
            .filter(t -> !(t instanceof Forbidden)));

  }
}
