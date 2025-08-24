package com.xpcosmos.transacoes_bancarias.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpcosmos.transacoes_bancarias.dto.ContaDTO;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.repositories.ContaRepository;

@Service
public class ContaService {

  @Autowired
  ContaRepository repository;

  public Conta createConta() {
    Long lastNumeroConta;
    try {
      lastNumeroConta = repository.findAll().getLast().getNumeroDeConta();
    } catch (NoSuchElementException e) {
      lastNumeroConta = 0l;
    }
    var newConta = new Conta(new ContaDTO(lastNumeroConta, 0));
    repository.save(newConta);
    return newConta;
  }

  public Conta deleteConta(Long numeroDeConta) throws NoSuchElementException {
    try {
      var contaToRemove = repository.findByNumeroDeConta(numeroDeConta);
      repository.delete(contaToRemove);
      return contaToRemove;
    } catch (NoSuchElementException e) {
      throw e;
    }

  }

}
