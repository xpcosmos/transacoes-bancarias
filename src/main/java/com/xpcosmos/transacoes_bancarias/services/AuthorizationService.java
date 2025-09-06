package com.xpcosmos.transacoes_bancarias.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;

@Service
public class AuthorizationService {

  RestTemplate restTemplate = new RestTemplate();
  HttpHeaders httpHeaders = new HttpHeaders();
  List<MediaType> listOfAcceptMediaTypes = List.of(MediaType.ALL);

  AuthorizationService() {
    httpHeaders.setAccept(listOfAcceptMediaTypes);
    httpHeaders.set("user-agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/26.0 Safari/605.1.15");
  }

  public AuthorizationDTO getAuthorizationResponse() {
    return restTemplate.getForObject("https://util.devi.tools/api/v2/authorize", AuthorizationDTO.class);
  }
}
