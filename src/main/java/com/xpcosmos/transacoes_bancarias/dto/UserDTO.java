package com.xpcosmos.transacoes_bancarias.dto;

public record UserDTO(
    String nomeCompleto,
    String cpf,
    String email,
    String senha) {
}