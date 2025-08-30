package com.xpcosmos.transacoes_bancarias.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.models.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureDataJpa
public class UserRepositoryTest extends UserTestResource{

  @Autowired
  UserRepository userRepository;

  @Autowired
  EntityManager entityManager;

	private User user;

	@BeforeEach
  void setUp(){
		user = gerarUserTest();
    entityManager.persist(user);
  }

  @Test
  void testFindByDocumentoId() {
      User resultUser = userRepository.findByDocumentoId(
      user.getDocumentoId()
      );
    assertEquals(user, resultUser);
  }

  @Test
  void testCreateUser() {
    assertDoesNotThrow(() -> userRepository.save(user));
  }


}
