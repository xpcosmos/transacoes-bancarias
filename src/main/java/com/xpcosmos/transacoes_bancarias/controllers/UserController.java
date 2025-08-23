package com.xpcosmos.transacoes_bancarias.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.Conta;
import com.xpcosmos.transacoes_bancarias.models.User;
import com.xpcosmos.transacoes_bancarias.services.ContaService;
import com.xpcosmos.transacoes_bancarias.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;
  @Autowired
  ContaService contaService;

  @PostMapping
  public ResponseEntity<User> postMethodName(@RequestBody UserDTO entity)  throws InternalError{
    Conta newConta = contaService.createConta();
    User response;
    try {
      response = userService.createUser(entity, newConta);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
      
    } catch (Exception e) {
      contaService.deleteConta(newConta.getNumeroDeConta());
      throw new InternalError();
    }
    
    
  }

}