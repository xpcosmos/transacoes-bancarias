package com.xpcosmos.transacoes_bancarias.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.Transacao;
import com.xpcosmos.transacoes_bancarias.models.operacoes.Operacao;
import com.xpcosmos.transacoes_bancarias.models.operacoes.OperacaoConsultaSaldo;
import com.xpcosmos.transacoes_bancarias.repositories.OperacaoRepository;
import com.xpcosmos.transacoes_bancarias.repositories.TransacaoRepository;

@Service
public class TransacaoService {

  @Autowired
  TransacaoRepository transacaoRepository;

  @Autowired
  OperacaoRepository operacaoRepository;

  public Transacao consultarSaldo(Conta conta){
    List<Operacao> listaDeOperacoes = List.of(
      new OperacaoConsultaSaldo(conta)
    );
    var transacao = new Transacao(listaDeOperacoes);
    return transacao;
  }


}
