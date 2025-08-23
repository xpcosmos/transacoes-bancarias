package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  // Create
  public User createUser(UserDTO user, Conta conta) {
    User newUser = new User(user);
    newUser.setConta(conta);
    return repository.save(newUser);
  }

  // Read
  public User getUserByCpf(String cpf) throws NotFoundException {
    return repository.findByCpf(cpf);
  }


}
