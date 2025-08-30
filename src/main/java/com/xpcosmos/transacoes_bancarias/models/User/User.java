package com.xpcosmos.transacoes_bancarias.models.User;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userTb")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String nomeCompleto;
  @Column(unique = true, nullable = false, updatable = false)
  private String documentoId;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
	@JsonIgnore
  private String senha;
	@Column(nullable = false, updatable = false)
	private UserType tipoUsuario;

  public User() {
  };

  public User(UserDTO user) {
    this.nomeCompleto = user.nomeCompleto();
    this.documentoId = user.documentoId();
    this.email = user.email();
		this.tipoUsuario = user.tipoUsuario();
    this.senha = user.senha();
  }

  public UUID getId() {
    return this.id;
  }

  public String getNomeCompleto() {
    return this.nomeCompleto;
  }

  public String getDocumentoId() {
    return this.documentoId;
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

  public void setDocumentoId(String documentoId) {
    this.documentoId = documentoId;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
