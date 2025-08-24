package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

  @Autowired
  UserRepository repository;

  // Create
  public User createUser(UserDTO user) throws DuplicateUserException {
    if (repository.existsByDocumentoId(user.documentoId())) {
      throw new DuplicateUserException();
    } else {
      User newUser = new User(user);
      return repository.save(newUser);
    }
  }

  // Read
  public User getUserByCpf(String documentoId) throws NotFoundException {
    return repository.findByDocumentoId(documentoId);
  }

  public User deleteUser(User user) {
    repository.deleteById(user.getId());
    return user;
  }

  public User assignContaToUser(User user, Conta conta) {
    user.setConta(conta);
    return repository.saveAndFlush(user);
  }

}
