package com.xpcosmos.transacoes_bancarias.models.operacoes;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

import com.xpcosmos.transacoes_bancarias.dto.OperacaoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Operacao {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(nullable = false)
  HttpStatus status;

  @Column(nullable = false)
  String tipo;

  @CreationTimestamp
  LocalDateTime createdOn;

  public Operacao(OperacaoDTO operacao) {
    this.status = HttpStatus.PROCESSING;
    this.tipo = operacao.tipo();
  }

  public Operacao() {
  }

  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String v) throws Exception {
    if (this.tipo != null) {
      this.tipo = v;
    } else {
      throw new Exception();
    }
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
