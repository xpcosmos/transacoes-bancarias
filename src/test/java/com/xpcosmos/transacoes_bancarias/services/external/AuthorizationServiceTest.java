package com.xpcosmos.transacoes_bancarias.services.external;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeoutException;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.annotation.Repeat;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.reactive.function.client.WebClientResponseException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

  @InjectMocks
  AuthorizationService authorizationService;

  @Test
  @Repeat(30)
  void testGetAuthorizationInstance() throws TimeoutException {
    authorizationService.getAuthorizationResponse()
        .doOnError(t -> assertEquals(t.getClass(), Forbidden.class))
        .doOnError(t -> assertNotEquals(t.getClass(), NotFound.class))
        .doOnSuccess(t -> assertInstanceOf(t.getClass(), AuthorizationDTO.class));

  }

  @Test
  @Repeat(30)
  void testResponseDataStructure() throws TimeoutException {
    authorizationService.getAuthorizationResponse()
        .doOnError(t -> assertEquals(t.getClass(), Forbidden.class))
        .doOnSuccess(t -> assertTrue(!t.data().isEmpty()));

  }
}
