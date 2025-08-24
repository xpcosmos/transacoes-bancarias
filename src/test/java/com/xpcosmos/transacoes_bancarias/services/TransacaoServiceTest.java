package com.xpcosmos.transacoes_bancarias.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpcosmos.transacoes_bancarias.exceptions.OperationFailed;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.operacoes.OperacaoConsultaSaldo;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
  
  @InjectMocks
  TransacaoService transacaoService;

  @Mock
  OperacaoConsultaSaldo operacaoConsultaSaldo;

  @Mock
  Conta conta;

  @Test
  void testExecutarConsultaSaldo__Sucesso(){
    when(conta.getSaldo()).thenReturn(19);
    boolean result = transacaoService.consultarSaldo(conta);
    assertEquals(true, result);
  }

  @Test
  void testExecutarConsultaSaldo__Falha(){
    when(conta.getSaldo()).thenThrow(new RuntimeException("Generic Exception"));
    assertThrowsExactly(OperationFailed.class, () -> transacaoService.consultarSaldo(conta));
  }

}
