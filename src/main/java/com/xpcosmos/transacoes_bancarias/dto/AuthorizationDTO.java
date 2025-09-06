package com.xpcosmos.transacoes_bancarias.dto;

import java.util.Map;

public record AuthorizationDTO(
  String status,
  Map<String, Boolean> data
) {}
