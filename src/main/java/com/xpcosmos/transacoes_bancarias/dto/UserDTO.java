package com.xpcosmos.transacoes_bancarias.dto;

public record UserDTO(
    String nomeCompleto,
    String documentoId,
    String email,
    String senha) {
}