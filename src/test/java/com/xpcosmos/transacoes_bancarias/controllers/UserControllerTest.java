package com.xpcosmos.transacoes_bancarias.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.xpcosmos.transacoes_bancarias.assets.UserTestCase;
import com.xpcosmos.transacoes_bancarias.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest extends UserTestCase{

  @InjectMocks UserController userController;
  private UserTestCase userTestCase = new UserTestCase();
  @Mock UserService userService;

  @Test
  void testPostMethodName() throws Exception{
    when(userService.createUser(userTestCase.userDTO)).thenReturn(userTestCase.user);
    var result = userController.postMethodName(userDTO);
    assertEquals(result.getStatusCode(), HttpStatus.CREATED);
  }
}
