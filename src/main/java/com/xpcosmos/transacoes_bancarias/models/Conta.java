package com.xpcosmos.transacoes_bancarias.models;

import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.dto.ContaDTO;

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
  private String numeroDeConta;
  private Number saldo;

  Conta(ContaDTO conta){
    this.numeroDeConta = conta.numeroDeConta();
    this.saldo = conta.saldo();
  }

  public String getNumeroDeConta() {
    return this.numeroDeConta;
  }

  public void setNumeroDeConta(String s){
    this.numeroDeConta = s;
  }

    public Number getSaldo() {
    return this.saldo;
  }

  public void setSaldo(Number n){
    this.saldo = n;
  }
}
