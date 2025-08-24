package com.xpcosmos.transacoes_bancarias.models;

import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.enums.StatusTransacionais;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacao {
  

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(nullable = false)
  StatusTransacionais status;

  Transacao(){
    this.status = StatusTransacionais.PROCESSANDO;
  }


}
