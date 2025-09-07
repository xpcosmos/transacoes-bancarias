package com.xpcosmos.transacoes_bancarias.exceptions;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;

public class SendingNotificationException extends RuntimeException {

  public TransacaoDTO transacaoDTO;

  public SendingNotificationException(TransacaoDTO transacaoDTO) {
    super("Falha no envio de notificação");
    this.transacaoDTO = transacaoDTO;
  }
}
