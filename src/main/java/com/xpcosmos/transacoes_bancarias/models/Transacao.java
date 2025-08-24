package com.xpcosmos.transacoes_bancarias.models;

import java.util.List;
import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.enums.StatusTransacionais;
import com.xpcosmos.transacoes_bancarias.models.operacoes.Operacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(nullable = false)
  StatusTransacionais status;

  @OneToMany
  List<Operacao> operacoes;

  public Transacao() {
  }

  public Transacao(Operacao[] operacoes) {
    this.status = StatusTransacionais.PROCESSANDO;
    this.operacoes = List.of(operacoes);
  }

  public Transacao(List<Operacao> operacoes) {
    this.status = StatusTransacionais.PROCESSANDO;
    this.operacoes = operacoes;
  }

}
