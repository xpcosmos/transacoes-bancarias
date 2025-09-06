package com.xpcosmos.transacoes_bancarias.models.permissions;

public abstract class TransferenciaPermission {
  private PermissionLevel creditarSaldo;
  private PermissionLevel debitarSaldo;

  TransferenciaPermission(PermissionLevel creditarSaldoPermissionLevel, PermissionLevel debitarSaldoPermissionLevel){
    this.creditarSaldo = creditarSaldoPermissionLevel;
    this.debitarSaldo = debitarSaldoPermissionLevel;
  }

  public PermissionLevel getCreditarSaldoPermissionLevel() {
    return creditarSaldo;
  }

  public PermissionLevel getDebitarSaldoPermissionLevel() {
    return debitarSaldo;
  }
}