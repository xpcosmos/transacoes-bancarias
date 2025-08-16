package com.xpcosmos.transacoes_bancarias.assets;

import java.util.Optional;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User;

public class UserTestCase {
  
  public UserDTO userDTO = new UserDTO(
      "User Teste",
      "000.000.000.21",
      "usertest@gmail.com",
      "userteste12!@");

  public User user = new User(userDTO);
}
