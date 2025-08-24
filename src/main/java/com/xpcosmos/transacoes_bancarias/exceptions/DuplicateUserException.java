package com.xpcosmos.transacoes_bancarias.exceptions;

public class DuplicateUserException extends RuntimeException {

  public DuplicateUserException() {
    super("Usuário já cadastrado");
  }
}
