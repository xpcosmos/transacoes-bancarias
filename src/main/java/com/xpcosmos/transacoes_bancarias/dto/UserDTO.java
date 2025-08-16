package com.xpcosmos.transacoes_bancarias.dto;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String nomeCompleto,
    String cpf,
    String email,
    String senha) {
}