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
    var newConta = new Conta(new ContaDTO(generateNewNumeroConta(), 0));
    repository.save(newConta);
    return newConta;
  }

  public Conta deleteConta(Conta conta) throws NoSuchElementException {
    try {
      repository.deleteById(conta.getId());
      return conta;
    } catch (NoSuchElementException e) {
      throw e;
    }
  }

  private Long generateNewNumeroConta() throws NoSuchElementException{
    try {
      return repository.findAll().getLast().getNumeroDeConta() + 1;
    } catch (NoSuchElementException e) {
      return 0l;
    }
    
  }

}
