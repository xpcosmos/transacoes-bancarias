package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.xpcosmos.transacoes_bancarias.repositories.ContaRepository;

public class ContaService {
  
  @Autowired
  ContaRepository repository;

}
