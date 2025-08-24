package com.xpcosmos.transacoes_bancarias.models.operacoes;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.xpcosmos.transacoes_bancarias.enums.StatusTransacionais;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.Transacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operacao")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @CreationTimestamp
  LocalDateTime createdOn;

  @Column(nullable = false)
  StatusTransacionais status;

  @Column
  String tipoOperacao;

  @ManyToOne
  @JoinColumn(name = "transacao_id", nullable = false, updatable = false)
  Transacao transacao;

  @ManyToOne
  @JoinColumn(name = "conta_id", nullable = false, updatable = false)
  Conta conta;

  Operacao() {
  }

  Operacao(String tipoOperacao, Conta conta) {
    this.conta = conta;
    this.tipoOperacao = tipoOperacao;
    this.status = StatusTransacionais.PROCESSANDO;
  }

  public abstract boolean executar() throws Exception;
}
