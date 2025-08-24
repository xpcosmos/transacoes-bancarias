package com.xpcosmos.transacoes_bancarias.models.operacoes;

import com.xpcosmos.transacoes_bancarias.exceptions.OperationFailed;
import com.xpcosmos.transacoes_bancarias.models.Conta;

public class OperacaoConsultaSaldo extends Operacao<Number> {

  public OperacaoConsultaSaldo(Conta conta) {
    super("Consulta", conta);
  }

  public boolean executar() throws OperationFailed {
    try {
      this.result = this.conta.getSaldo();
      return true;
    } catch (Exception e) {
      throw new OperationFailed();
    }
  }

}
