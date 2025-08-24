package com.xpcosmos.transacoes_bancarias.exceptions;

public class OperationFailed extends RuntimeException{
  public OperationFailed(){
    super("Operação falhou");
  }
}
