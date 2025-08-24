package com.xpcosmos.transacoes_bancarias.models;

import java.util.List;
import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.models.operacoes.Operacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Transacao {
  @Id
  UUID id;
  
  @OneToMany(cascade = CascadeType.REFRESH)
  Conta target;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_operacao" )
  List<Operacao<?>> operacoes;

  

}
