package com.xpcosmos.transacoes_bancarias.models;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Transacao {
  @Id
  UUID id;
  
  @OneToMany(cascade = CascadeType.REFRESH)
  Conta target;

  @OneToMany
  Operacao operacao;


}
