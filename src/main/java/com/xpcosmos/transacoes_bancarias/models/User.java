package com.xpcosmos.transacoes_bancarias.models;

import java.util.UUID;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String nomeCompleto;
  @Column(unique = true, nullable = false, updatable = false)
  private String cpf;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String senha;

  public User() {
  };

  public User(UserDTO user) {
    this.nomeCompleto = user.nomeCompleto();
    this.cpf = user.cpf();
    this.email = user.email();
    this.senha = user.senha();
  }

  public UUID getId() {
    return this.id;
  }

  public String getNomeCompleto() {
    return this.nomeCompleto;
  }

  public String getCpf() {
    return this.cpf;
  }

  public String getEmail() {
    return this.email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
