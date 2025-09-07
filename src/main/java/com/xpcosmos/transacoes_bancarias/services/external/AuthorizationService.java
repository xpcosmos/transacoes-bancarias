package com.xpcosmos.transacoes_bancarias.services.external;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class AuthorizationService extends ExternalService {

  public Mono<AuthorizationDTO> getAuthorizationResponse() {
    try {
      return webClient.get().uri(getUri()).retrieve().bodyToMono(AuthorizationDTO.class)
          .retryWhen(Retry.fixedDelay(3l, Duration.ofSeconds(15)));
    } catch (Forbidden e) {
      throw new ExternalServiceException();
    }
  }

  @Override
  public String getUri() {
    return "https://util.devi.tools/api/v2/authorize";
  }
}
