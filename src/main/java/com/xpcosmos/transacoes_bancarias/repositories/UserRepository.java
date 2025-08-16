package com.xpcosmos.transacoes_bancarias.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xpcosmos.transacoes_bancarias.models.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
  
  public Optional<User> findByCpf(String cpf);
}
