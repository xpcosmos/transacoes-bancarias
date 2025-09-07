package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class AuthorizationService extends ExternalServiceWebClient{



  public Mono<AuthorizationDTO> getAuthorizationResponse() {
    try {
      return webClient.get().uri("v2/authorize").retrieve().bodyToMono(AuthorizationDTO.class)
          .retryWhen(Retry.fixedDelay(3l, Duration.ofSeconds(15)));
    } catch (Forbidden e) {
      throw new ExternalServiceException();
    }
  }
}
