package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.exceptions.NotEnoughMoneyException;
import com.xpcosmos.transacoes_bancarias.models.User.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferenciaService {

	@Autowired
	UserService userService;

	public void tranferir(TransacaoDTO transacaoDTO) throws Exception {
		User pagador = userService.getUserById(transacaoDTO.payer());
		User beneficiario = userService.getUserById(transacaoDTO.payee());
		Float valorTransferencia = transacaoDTO.value();

		validarSaldoSuficiente(pagador.getSaldo(), valorTransferencia);
		valdidarValorTransferencia(valorTransferencia);
		creditarConta(pagador.getId(), valorTransferencia);
		debitarConta(beneficiario.getId(), valorTransferencia);
	}

	private void validarSaldoSuficiente(Float saldo, Float valorTransferencia) {
		if (saldo < valorTransferencia) {
			throw new NotEnoughMoneyException();
		}
	}

	private void valdidarValorTransferencia(Float valor) {
		if (valor <= 0) {
			throw new InvalidOperationException();
		}
	}

	private void creditarConta(Long id, Float valor) throws Exception {
		userService.incrementarSaldo(id, valor);
	}

	private void debitarConta(Long id, Float valor) throws Exception {
		userService.incrementarSaldo(id, -valor);
	}

}
