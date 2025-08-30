package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpcosmos.transacoes_bancarias.models.User.User;


public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByDocumentoId(String documentoId);
  boolean existsByDocumentoId(String documentoId);
}
