package com.xpcosmos.transacoes_bancarias.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;

@Service
public class AuthorizationService {

  RestTemplate restTemplate = new RestTemplate();
  HttpHeaders httpHeaders = new HttpHeaders();

  String uri = "https://util.devi.tools/api/v2/authorize";

  List<MediaType> listOfAcceptMediaTypes = List.of(MediaType.ALL);

  public ResponseEntity<AuthorizationDTO> getAuthorizationResponse() {
    HttpEntity<HttpHeaders> entity = new HttpEntity<>(getHttpHeaders());
    try {
      return  restTemplate.exchange(uri,HttpMethod.GET,entity, AuthorizationDTO.class);
    } catch (Forbidden e) {
      throw new ExternalServiceException();
    }
  }

  private HttpHeaders getHttpHeaders(){
    httpHeaders.setAccept(listOfAcceptMediaTypes);
    httpHeaders.add("user-agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/26.0 Safari/605.1.15");
    return httpHeaders;
  }

}
