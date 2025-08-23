package com.xpcosmos.transacoes_bancarias.models;

import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.dto.ContaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID Id;
  @Column(unique = true, nullable = false, updatable = false)
  private String numeroDeConta;
  @Column(unique = true, nullable = false, precision = 2, updatable = true)
  private Number saldo;

  Conta() {
  }

  Conta(ContaDTO conta) {
    this.numeroDeConta = conta.numeroDeConta();
    this.saldo = conta.saldo();
  }

  @Column(unique = true, nullable = false)
  public String getNumeroDeConta() {
    return this.numeroDeConta;
  }

  public void setNumeroDeConta(String s) {
    this.numeroDeConta = s;
  }

  public Number getSaldo() {
    return this.saldo;
  }

  public void setSaldo(Number n) {
    this.saldo = n;
  }
}
