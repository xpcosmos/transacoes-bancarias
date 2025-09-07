package com.xpcosmos.transacoes_bancarias.dto;

public record NonCriticalExecptionDTO<T>(
  T result,
  String cause
) {}
