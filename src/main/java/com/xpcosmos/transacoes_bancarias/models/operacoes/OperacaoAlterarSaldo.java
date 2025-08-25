package com.xpcosmos.transacoes_bancarias.models.operacoes;

import com.xpcosmos.transacoes_bancarias.models.Conta;

public abstract class OperacaoAlterarSaldo extends Operacao<Number> {
  Number saldoAnterior;

  OperacaoAlterarSaldo(String tipoOperacao, Conta conta) {
    super(tipoOperacao, conta);
    this.saldoAnterior = conta.getSaldo();
  }

  void rollback() {
    conta.setSaldo(saldoAnterior);
  };


}