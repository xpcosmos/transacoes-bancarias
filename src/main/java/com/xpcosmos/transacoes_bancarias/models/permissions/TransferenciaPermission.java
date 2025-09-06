package com.xpcosmos.transacoes_bancarias.models.permissions;

public interface TransferenciaPermission {
  public PermissionLevel getCreditarSaldoPermissionLevel();

  public PermissionLevel getDebitarSaldoPermissionLevel();

}