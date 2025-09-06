package com.xpcosmos.transacoes_bancarias.models.roles;

import com.xpcosmos.transacoes_bancarias.models.permissions.PermissionLevel;
import com.xpcosmos.transacoes_bancarias.models.permissions.TransferenciaPermission;

public abstract class LojistaRole implements TransferenciaPermission {

  @Override
  public PermissionLevel getCreditarSaldoPermissionLevel() {
    return PermissionLevel.ALLOW;
  }

  @Override
  public PermissionLevel getDebitarSaldoPermissionLevel() {
    return PermissionLevel.DENY;
  }
}
