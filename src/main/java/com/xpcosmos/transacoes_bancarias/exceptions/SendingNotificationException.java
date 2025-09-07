package com.xpcosmos.transacoes_bancarias.exceptions;

public class SendingNotificationException extends RuntimeException{

  public SendingNotificationException(){
    super("Falha no envio de notificação");
  }
}
