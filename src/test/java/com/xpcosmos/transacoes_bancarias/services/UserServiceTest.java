package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends UserTestResource {

  @InjectMocks
  UserService service;
  @Mock
  UserRepository repository;

	User user;
	UserDTO userDto;

	@BeforeEach
	void createUsers(){
		user = gerarUserTest(null, null);
		userDto = gerarUserTestDTO(null, null);
	}

  @Test
  void testUserDuplicatedCreation() throws Exception {
    when(repository.existsByDocumentoId(ArgumentMatchers.anyString())).thenReturn(true);
    assertThrowsExactly(DuplicateUserException.class, () -> service.createUser(userDto));
  };

	@Test
  void testUserAtulizarSaldo() throws Exception {

		Float saldoParaCreditar = 10f;
		Float saldoInicial = user.getSaldo();

    when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		service.updateValor(user.getId(), saldoParaCreditar);
		User updatedUser = service.getUserById(user.getId());

		assertEquals(saldoParaCreditar, updatedUser.getSaldo());
		assertNotEquals(saldoInicial, updatedUser.getSaldo());


  };



}
