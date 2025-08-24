package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.xpcosmos.transacoes_bancarias.models.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, UUID> {

}