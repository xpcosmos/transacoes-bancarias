package com.xpcosmos.transacoes_bancarias.models.operacoes;

import com.xpcosmos.transacoes_bancarias.models.Conta;

public class OperacaoConsultaSaldo extends Operacao {

  OperacaoConsultaSaldo(Conta conta) {
    super("Consulta", conta);
  }

  public boolean executar() throws Exception{
    try {
      this.conta.getSaldo();
      return true;
    } catch (Exception e) {
      throw new Exception();
    }
  }

}
