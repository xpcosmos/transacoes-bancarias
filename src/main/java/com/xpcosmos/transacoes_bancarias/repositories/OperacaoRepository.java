package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.xpcosmos.transacoes_bancarias.models.operacoes.Operacao;

public interface OperacaoRepository extends CrudRepository<Operacao,UUID>{
  
}
