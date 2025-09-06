package com.xpcosmos.transacoes_bancarias.models.roles;

import com.xpcosmos.transacoes_bancarias.models.permissions.PermissionLevel;
import com.xpcosmos.transacoes_bancarias.models.permissions.TransferenciaPermission;

public class LojistaRole extends TransferenciaPermission {

  LojistaRole(){
    this.setCreditarSaldo(PermissionLevel.UPDATE);
  }

  
}
