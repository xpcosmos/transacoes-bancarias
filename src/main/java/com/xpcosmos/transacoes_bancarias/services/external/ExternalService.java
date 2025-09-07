package com.xpcosmos.transacoes_bancarias.services.external;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class ExternalService {

  public final WebClient webClient =  WebClient.create();

  List<MediaType> listOfAcceptMediaTypes = List.of(MediaType.ALL);

  public abstract String getUri();
}
