package com.xpcosmos.transacoes_bancarias.services.external;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

  @InjectMocks
  NotificationService notificationService;

  @Test
  void testGetNotificationResponse() {
    String response = notificationService.getNotificationResponse().block();
    assertEquals(null, response);
  }
}
