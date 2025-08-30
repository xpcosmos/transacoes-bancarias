package com.xpcosmos.transacoes_bancarias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferenciaService {

	@Autowired
	UserService userService;


}
