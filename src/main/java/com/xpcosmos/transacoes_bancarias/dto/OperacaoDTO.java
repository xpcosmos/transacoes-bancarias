package com.xpcosmos.transacoes_bancarias.dto;

import org.springframework.http.HttpStatus;

public record OperacaoDTO(String tipo,HttpStatus status) {}
