package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpcosmos.transacoes_bancarias.assets.UserTestCase;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends UserTestCase {

  @InjectMocks
  UserService service;
  @Mock
  UserRepository repository;

  @Test
  void testCreateUser() throws Exception {
    when(repository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
    User result = service.createUser(userDTO);
    assertNotNull(result);
  }

}
