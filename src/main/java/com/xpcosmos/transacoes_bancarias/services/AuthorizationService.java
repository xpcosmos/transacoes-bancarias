package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;

@Service
public class AuthorizationService {

  RestTemplate restTemplate = new RestTemplate();

  public AuthorizationDTO getAuthorizationResponse() {
    return restTemplate.getForObject("https://util.devi.tools/api/v2/authorize", AuthorizationDTO.class);

  }
}
