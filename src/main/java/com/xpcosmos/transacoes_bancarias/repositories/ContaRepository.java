package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpcosmos.transacoes_bancarias.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, UUID> {}
