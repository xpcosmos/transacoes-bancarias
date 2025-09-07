package com.xpcosmos.transacoes_bancarias.services.external;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public abstract class ExternalService {

  public final RestTemplate restTemplate = new RestTemplate();
  private HttpHeaders httpHeaders = new HttpHeaders();

  List<MediaType> listOfAcceptMediaTypes = List.of(MediaType.ALL);

  public HttpHeaders getHttpHeaders(){
    httpHeaders.setAccept(listOfAcceptMediaTypes);
    httpHeaders.add("user-agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/26.0 Safari/605.1.15");
    return httpHeaders;
  }

  public abstract String getUri();
}
