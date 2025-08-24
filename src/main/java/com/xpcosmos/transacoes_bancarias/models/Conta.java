package com.xpcosmos.transacoes_bancarias.models;

import java.util.List;
import java.util.UUID;


import com.xpcosmos.transacoes_bancarias.dto.ContaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID Id;
  
  @Column(unique = true, nullable = false, updatable = false)
  private Long numeroDeConta;

  @Column(nullable = false, precision = 2, updatable = true)
  private Number saldo;

  @OneToOne(mappedBy = "conta",cascade = CascadeType.ALL)
  private User user;
  public Conta() {
  }

  @OneToMany
  List<Transacao> transacoes;

  public Conta(ContaDTO conta) {
    this.numeroDeConta = conta.numeroDeConta();
    this.saldo = conta.saldo();
  }


  public UUID getId(){
    return this.Id;
  }

  public Long getNumeroDeConta() {
    return this.numeroDeConta;
  }

  public void setNumeroDeConta(Long s) {
    this.numeroDeConta = s;
  }

  public Number getSaldo() {
    return this.saldo;
  }

  public void setSaldo(Number n) {
    this.saldo = n;
  }
}
