package com.xpcosmos.transacoes_bancarias.models.operacoes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xpcosmos.transacoes_bancarias.models.Conta;

@ExtendWith(MockitoExtension.class)
public class OperacaoConsultaSaldoTest {

  @InjectMocks
  OperacaoConsultaSaldo operacaoConsultaSaldo;

  @Mock
  Conta conta;

  @Test
  void testExecutarComSucesso() {

    when(conta.getSaldo()).thenReturn(10);

    var operacaoConsultaSaldo = new OperacaoConsultaSaldo(conta);
    boolean result;
    try {
      result = operacaoConsultaSaldo.executar();
    } catch (Exception e) {
      result = false;
    }
    assertEquals(true, result);
  }

  @Test
  void testExecutarComFalha() {
    when(conta.getSaldo()).thenThrow(new Exception());
    var operacaoConsultaSaldo = new OperacaoConsultaSaldo(conta);
    assertThrows(Exception.class, () -> operacaoConsultaSaldo.executar());
  }
}
