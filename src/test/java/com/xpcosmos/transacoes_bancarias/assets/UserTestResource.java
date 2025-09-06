package com.xpcosmos.transacoes_bancarias.assets;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.models.User.UserType;

public class UserTestResource extends TestResourceEntity<UserDTO, User> {

  private UserType userType = UserType.USUARIO;
  private Float saldo = 0f;

  public UserDTO gerarDTO() {
    String randomString = randomString();
    String nomeCompleto = "User Teste";
    String email = "usertest" + randomString + "@gmail.com";
    String senha = "senha";
    return new UserDTO(nomeCompleto, randomString, email, userType, saldo, senha);
  };

  public User gerarEntity() {
    return new User(gerarDTO());
  }

  public Float getSaldo() {
    return saldo;
  }

  public void setSaldo(Float saldo) {
    this.saldo = saldo;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
