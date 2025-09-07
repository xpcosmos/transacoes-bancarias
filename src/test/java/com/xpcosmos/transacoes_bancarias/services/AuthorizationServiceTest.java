package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;
import com.xpcosmos.transacoes_bancarias.services.external.AuthorizationService;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

  @InjectMocks
  AuthorizationService authorizationService;

  @Test
  void testGetAuthorizationResponse() throws ExternalServiceException {
    try {
      var response = authorizationService.getAuthorizationResponse();
      assertNotNull(response);
      assertTrue(response.hasBody());
    } catch (ExternalServiceException e) {
      assertFalse(e.getMessage().isBlank());
    }
  }

  @Test
  void testResponseDataStructure() throws HttpClientErrorException {
    try {
      ResponseEntity<AuthorizationDTO> authorization = authorizationService.getAuthorizationResponse();
      AuthorizationDTO reponse = authorization.getBody();
      assertNotNull(reponse);
      assertTrue(reponse.data().containsKey("authorization"));
    } catch (ExternalServiceException e) {
      assertFalse(e.getMessage().isBlank());
    }

  }
}
