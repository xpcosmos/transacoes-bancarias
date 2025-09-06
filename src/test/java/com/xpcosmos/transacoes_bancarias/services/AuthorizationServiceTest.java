package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

  @InjectMocks
  AuthorizationService authorizationService;

  @Test
  void testGetAuthorizationResponse() {
    assertDoesNotThrow(() -> authorizationService.getAuthorizationResponse());
  }
}
