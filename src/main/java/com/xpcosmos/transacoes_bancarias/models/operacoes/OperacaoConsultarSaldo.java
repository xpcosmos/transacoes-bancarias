package com.xpcosmos.transacoes_bancarias.models.operacoes;

import org.springframework.http.HttpStatus;

import com.xpcosmos.transacoes_bancarias.models.Conta;

public class OperacaoConsultarSaldo extends Operacao<Number> {

  Conta conta;
  Number result = null;

  public OperacaoConsultarSaldo(Conta conta) {
    this.conta = conta;
  }

  public boolean executar() {
    try {
      this.status = HttpStatus.OK;
      this.result = this.conta.getSaldo();
      return true;
    } catch (Exception e) {
      this.status = HttpStatus.BAD_REQUEST;
      return false;
    }
  }

  public Number getResult() {
    return this.result.floatValue();

  }
}
