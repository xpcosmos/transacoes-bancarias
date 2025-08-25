package com.xpcosmos.transacoes_bancarias.models.operacoes;

import com.xpcosmos.transacoes_bancarias.exceptions.OperationFailed;
import com.xpcosmos.transacoes_bancarias.models.Conta;

public class OperacaoCreditarSaldo extends OperacaoAlterarSaldo{
  
  public OperacaoCreditarSaldo(Conta conta, Number valor){
    super("Adicionar Saldo", conta);
  }

  @Override
  public boolean executar() throws OperationFailed {
    return false;
  }
}
