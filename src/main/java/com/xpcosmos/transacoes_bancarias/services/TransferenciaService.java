package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.exceptions.NotEnoughMoneyException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.models.User.UserType;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional
public class TransferenciaService {

  @Autowired
  UserService userService;

  @Transactional(value = TxType.REQUIRES_NEW)
  public void tranferir(TransacaoDTO transacaoDTO) throws Exception {

    User beneficiario = userService.getUserById(transacaoDTO.payee());
    User pagador = userService.getUserById(transacaoDTO.payer());

    if (pagador.getTipoUsuario() == UserType.LOJISTA) {
      throw new InvalidOperationException();
    }

    Float valorTransferencia = transacaoDTO.value();
    validarSaldoSuficiente(pagador.getSaldo(), valorTransferencia);
    valdidarValorTransferencia(valorTransferencia);
    creditarConta(pagador.getId(), valorTransferencia);
    debitarConta(beneficiario.getId(), valorTransferencia);

  }

  void validarSaldoSuficiente(Float saldo, Float valorTransferencia) {
    if (saldo < valorTransferencia) {
      throw new NotEnoughMoneyException();
    }
  }

  void valdidarValorTransferencia(Float valor) {
    if (valor <= 0) {
      throw new InvalidOperationException();
    }
  }

  public void creditarConta(Long id, Float valor) throws Exception {
    userService.incrementarSaldo(id, valor);
  }

  public void debitarConta(Long id, Float valor) throws Exception {
    userService.incrementarSaldo(id, -valor);
  }

}
