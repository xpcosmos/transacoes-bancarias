package com.xpcosmos.transacoes_bancarias.services.external;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;

@Service
public class AuthorizationService extends ExternalService{



  public ResponseEntity<AuthorizationDTO> getAuthorizationResponse() {
    HttpEntity<HttpHeaders> entity = new HttpEntity<>(getHttpHeaders());
    try {
      return  restTemplate.exchange(getUri(),HttpMethod.GET,entity, AuthorizationDTO.class);
    } catch (Forbidden e) {
      throw new ExternalServiceException();
    }
  }

  @Override
  public String getUri() {
    return "https://util.devi.tools/api/v2/authorize";
  }
}
