package com.xpcosmos.transacoes_bancarias.dto;

import com.xpcosmos.transacoes_bancarias.models.Conta;

public record TransacaoDTO(Conta from, Conta to, Number value) {
}
