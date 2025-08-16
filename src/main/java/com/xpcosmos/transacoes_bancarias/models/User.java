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
    this.id = user.id();
    this.nomeCompleto = user.nomeCompleto();
    this.cpf = user.cpf();
    this.email = user.email();
    this.senha = user.senha();
  }

  String getNomeCompleto() {
    return this.nomeCompleto;
  }

  void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  String getCpf() {
    return this.cpf;
  }

  void setCpf(String cpf) {
    this.cpf = cpf;
  }

  String getEmail() {
    return this.email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  String getSenha() {
    return this.senha;
  }

  void setSenha(String senha) {
    this.senha = senha;
  }

}
