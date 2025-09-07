package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException.Forbidden;

import com.xpcosmos.transacoes_bancarias.dto.AuthorizationDTO;
import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.exceptions.NotEnoughMoneyException;
import com.xpcosmos.transacoes_bancarias.exceptions.SendingNotificationException;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.models.User.UserType;
import com.xpcosmos.transacoes_bancarias.services.external.AuthorizationService;
import com.xpcosmos.transacoes_bancarias.services.external.NotificationService;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class TransferenciaService {

  @Autowired
  UserService userService;
  @Autowired
  AuthorizationService authorizationService;
  @Autowired
  NotificationService notificationService;

  @Transactional(value = TxType.REQUIRES_NEW, dontRollbackOn = SendingNotificationException.class)
  public void tranferir(TransacaoDTO transacaoDTO) throws NotFoundException {

    User beneficiario = userService.getUserById(transacaoDTO.payee());
    User pagador = userService.getUserById(transacaoDTO.payer());
    Float valorTransferencia = transacaoDTO.value();

    validarTipoPagador(pagador);

    Mono<AuthorizationDTO> authorization = getAuthorization();

    validarSaldoSuficiente(pagador.getSaldo(), valorTransferencia);
    valdidarValorTransferencia(valorTransferencia);

    try {
      creditarConta(pagador.getId(), valorTransferencia);
      debitarConta(beneficiario.getId(), valorTransferencia);
      authorization.block();
      sendNotification(transacaoDTO);
    } catch (Forbidden e) {
      throw new ExternalServiceException();
    }

  }

  void sendNotification(TransacaoDTO transacaoDTO) {
    try {
      notificationService.getNotificationResponse();
    } catch (Exception e) {
      throw new SendingNotificationException(transacaoDTO);
    }
  }

  void validarSaldoSuficiente(Float saldo, Float valorTransferencia) {
    if (saldo < valorTransferencia) {
      throw new NotEnoughMoneyException();
    }
  }

  void validarTipoPagador(User pagador) {
    if (pagador.getTipoUsuario() == UserType.LOJISTA) {
      throw new InvalidOperationException();
    }
  }

  void valdidarValorTransferencia(Float valor) {
    if (valor <= 0) {
      throw new InvalidOperationException();
    }
  }

  Mono<AuthorizationDTO> getAuthorization() throws Forbidden {
    return authorizationService.getAuthorizationResponse();
  }

  public void creditarConta(Long id, Float valor) throws NotFoundException {
    userService.incrementarSaldo(id, valor);
  }

  public void debitarConta(Long id, Float valor) throws NotFoundException {
    userService.incrementarSaldo(id, -valor);
  }

}
