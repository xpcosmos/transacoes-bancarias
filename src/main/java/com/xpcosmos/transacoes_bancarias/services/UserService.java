package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  // Create
  User createUser(UserDTO user) {
    User newUser = new User(user);
    return repository.save(newUser);
  }

  // Read
  User getUserByCpf(String cpf) throws NotFoundException {
    return repository.findByCpf(cpf);
  }


}
