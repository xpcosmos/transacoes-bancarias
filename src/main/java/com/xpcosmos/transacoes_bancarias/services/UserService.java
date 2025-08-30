package com.xpcosmos.transacoes_bancarias.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

  @Autowired
  UserRepository repository;

  public User createUser(UserDTO user) throws DuplicateUserException{
    User newUser = new User(user);
		if (repository.existsByDocumentoId(newUser.getDocumentoId())){
			throw new DuplicateUserException();
		} else{
			return newUser;
		}

  }

  // Read
  public User getUserByDocumentoId(String documentoId) throws NotFoundException {
    return repository.findByDocumentoId(documentoId);
  }

  public UUID deleteUser(UUID id) {
    repository.deleteById(id);
    return id;
  }


}
