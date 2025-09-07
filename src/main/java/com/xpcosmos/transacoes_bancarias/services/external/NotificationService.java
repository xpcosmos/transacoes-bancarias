package com.xpcosmos.transacoes_bancarias.services.external;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class NotificationService extends ExternalService{

  public ResponseEntity<String> getNotificationResponse(){
    return restTemplate.exchange(getUri(), HttpMethod.POST, null, String.class);
  }

  @Override
  public String getUri() {
    return "https://util.devi.tools/api/v1/notify";
  }
}
