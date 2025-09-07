package com.xpcosmos.transacoes_bancarias.exceptions;

public class ExternalServiceException extends RuntimeException{

  public ExternalServiceException(){
    super("Erro em requisição originada no servidor externo!");
  }
}
