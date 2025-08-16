package com.xpcosmos.transacoes_bancarias.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.xpcosmos.transacoes_bancarias.assets.UserTestCase;
import com.xpcosmos.transacoes_bancarias.models.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureDataJpa
public class UserRepositoryTest{

  @Autowired
  UserRepository userRepository;

  @Autowired
  EntityManager entityManager;

  private UserTestCase UserEntity = new UserTestCase();

  @Test
  void testFindByCpf() {
      User resultUser = userRepository.findByCpf(
      UserEntity.user.getCpf()
      );
    assertEquals(UserEntity.user, resultUser);
  }

  @Test
  void testCreateUser() {
    assertDoesNotThrow(() -> userRepository.save(UserEntity.user));
  }

  @BeforeEach
  private void createUser(){
    entityManager.persist(UserEntity.user);
  }
}
