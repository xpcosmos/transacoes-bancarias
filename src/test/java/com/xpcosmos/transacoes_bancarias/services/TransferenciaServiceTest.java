package com.xpcosmos.transacoes_bancarias.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.NotEnoughMoneyException;
import com.xpcosmos.transacoes_bancarias.models.User.User;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest extends UserTestResource {

	@Mock
	UserService userService;
	@InjectMocks
	TransferenciaService transferenciaService;

	@Test
	void testExceptionSaldoInsuficiente() throws Exception {
		User user = gerarUserTest(null, null);
		TransacaoDTO transacaoDTO = new TransacaoDTO(1l, user.getId(), 10f);
		when(userService.getUserById(user.getId())).thenReturn(user);
		assertThrowsExactly(NotEnoughMoneyException.class, () -> transferenciaService.tranferir(transacaoDTO));

	}
}
