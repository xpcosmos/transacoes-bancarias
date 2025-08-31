package com.xpcosmos.transacoes_bancarias.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional
public class UserService {

  @Autowired
  UserRepository repository;

  @Transactional
  public User createUser(UserDTO user) throws DuplicateUserException {
    User newUser = new User(user);
    if (repository.existsByDocumentoId(newUser.getDocumentoId())) {
      throw new DuplicateUserException();
    } else {
      repository.save(newUser);
      return newUser;
    }
  }

  @Transactional(value = TxType.REQUIRES_NEW)
  public List<User> batchCreateUsers(List<UserDTO> listOfUserDTOs) throws DuplicateUserException {
    List<User> listOfUsers = listOfUserDTOs.stream().map(e -> new User(e)).toList();
    try {
      return repository.saveAll(listOfUsers);
    } catch (Exception e) {
      throw new DuplicateUserException("Um ou mais usuário estão duplicados ou já existem na base");
    }

  }

  public Float incrementarSaldo(Long id, Float valor) throws InvalidOperationException, NotFoundException {
    User user = getUserById(id);
    Float saldoAtual = user.getSaldo();
    saldoAtual += valor;
    user.setSaldo(saldoAtual);
    repository.save(user);
    return saldoAtual;
  }

  public List<User> getAllUsers() {
    List<User> users = repository.findAll();
    return users;
  }

  public User getUserByDocumentoId(String documentoId) throws NotFoundException {
    return repository.findByDocumentoId(documentoId).orElseThrow(() -> new NotFoundException());
  }

  public User getUserById(Long id) throws NotFoundException {
    return repository.findById(id).orElseThrow(() -> new NotFoundException());
  }

  public Long deleteUser(Long id) {
    repository.deleteById(id);
    return id;
  }

}
