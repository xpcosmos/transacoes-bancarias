package com.xpcosmos.transacoes_bancarias.services.external;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

  @InjectMocks
  AuthorizationService authorizationService;

  @Test
  void testGetAuthorizationResponse() throws TimeoutException {
      var request = authorizationService.getAuthorizationResponse();
      assertDoesNotThrow(() -> request.blockOptional().orElseThrow(() -> new TimeoutException()));
  }

  @Test
  void testResponseDataStructure() throws TimeoutException {
    var request = authorizationService.getAuthorizationResponse().block();
    assertNotNull(request.data());
    assertTrue(request.data().containsKey("authorization"));

  }
}
