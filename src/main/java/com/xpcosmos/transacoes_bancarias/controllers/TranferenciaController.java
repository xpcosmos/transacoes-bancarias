package com.xpcosmos.transacoes_bancarias.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transfer")
public class TranferenciaController {

	@Autowired
	TransferenciaService service;

	@PostMapping
	public ResponseEntity<HttpStatus> tranferir(@RequestBody TransacaoDTO transacaoDTO) throws Exception{
		service.tranferir(transacaoDTO);
		return ResponseEntity.ok().body(null);
	}

}
