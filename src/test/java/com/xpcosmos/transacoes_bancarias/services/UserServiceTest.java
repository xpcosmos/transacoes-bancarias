package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


  @InjectMocks
  UserService service;
  @Mock
  UserRepository repository;

  private UserDTO userDTO = new UserDTO(
      "User Teste",
      "000.000.000.21",
      "usertest@gmail.com",
      "userteste12!@");

  private User user = new User(userDTO);

  @Test
  void testCreateUser() throws Exception{
    when(repository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
    User result = service.createUser(userDTO);
    assertNotNull(result);
  }

}
