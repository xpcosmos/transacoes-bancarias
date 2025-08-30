package com.xpcosmos.transacoes_bancarias.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

  public NotEnoughMoneyException() {
    super("Saldo insuficiente para transação");
  }
}
