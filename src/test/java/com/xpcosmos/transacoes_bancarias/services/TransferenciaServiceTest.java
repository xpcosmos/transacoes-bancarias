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
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.exceptions.NotEnoughMoneyException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.models.User.UserType;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest extends UserTestResource {

  @Mock
  UserService userService;
  @InjectMocks
  TransferenciaService transferenciaService;

  @Test
  @Transactional
  void testExceptionSaldoInsuficiente() throws Exception {
    User pagador = gerarEntity();
    User beneficiario = gerarEntity();

    Long pagadorId = pagador.getId();
    Long beneficiarioId = beneficiario.getId();

    when(userService.getUserById(pagadorId)).thenReturn(pagador);
    when(userService.getUserById(beneficiarioId)).thenReturn(beneficiario);
    TransacaoDTO transacaoDTO = new TransacaoDTO(beneficiarioId, pagadorId, 10f);
    assertThrowsExactly(NotEnoughMoneyException.class, () -> transferenciaService.tranferir(transacaoDTO));
  }

  @Test
  void testExceptionValorNegativo() throws Exception {

    User pagador = gerarEntity();
    pagador.setSaldo(20f);
    User beneficiario = gerarEntity();
    TransacaoDTO transacaoDTO = new TransacaoDTO(pagador.getId(), beneficiario.getId(), -10f);

    when(userService.getUserById(pagador.getId())).thenReturn(pagador);
    when(userService.getUserById(beneficiario.getId())).thenReturn(beneficiario);

    assertThrowsExactly(InvalidOperationException.class, () -> transferenciaService.tranferir(transacaoDTO));

  }

  @Test
  void testExceptionLogistaComoPagador() throws Exception {
    User pagador = gerarEntity();
    User beneficiario = gerarEntity();

    Long pagadorId = pagador.getId();
    Long beneficiarioId = beneficiario.getId();

    pagador.setTipoUsuario(UserType.LOJISTA);

    when(userService.getUserById(beneficiarioId)).thenReturn(beneficiario);
    when(userService.getUserById(pagadorId)).thenReturn(pagador);

    assertThrows(Exception.class, () -> transferenciaService.tranferir(
        new TransacaoDTO(pagadorId, beneficiarioId, 10f)));

  }
}
