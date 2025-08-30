package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public User createUser(UserDTO user) throws DuplicateUserException {
		User newUser = new User(user);
		if (repository.existsByDocumentoId(newUser.getDocumentoId())) {
			throw new DuplicateUserException();
		} else {
			return newUser;
		}
	}

	public Double creditarValor(Long id, Float valor){
		try {
			User user = getUserById(id);
			Float saldoAtual = user.getSaldo();
			user.setSaldo(valor);
		} catch (NotFoundException e) {
			throw new InvalidOperationException();
		}

		return 0d;
	}

	// Read
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
