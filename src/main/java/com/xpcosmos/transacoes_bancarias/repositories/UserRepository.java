package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpcosmos.transacoes_bancarias.models.User.User;


public interface UserRepository extends JpaRepository<User, UUID> {
  User findByDocumentoId(String documentoId);
  boolean existsByDocumentoId(String documentoId);
}
