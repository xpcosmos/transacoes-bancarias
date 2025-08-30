package com.xpcosmos.transacoes_bancarias.dto;

import com.xpcosmos.transacoes_bancarias.models.User.UserType;


public record UserDTO(
    String nomeCompleto,
    String documentoId,
    String email,
		UserType tipoUsuario,
    String senha) {
}