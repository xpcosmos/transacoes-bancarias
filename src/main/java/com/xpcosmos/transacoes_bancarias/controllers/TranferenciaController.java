package com.xpcosmos.transacoes_bancarias.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transfer")
@Transactional
public class TranferenciaController {

	@Autowired
	TransferenciaService service;

	@PostMapping
	public ResponseEntity<TransacaoDTO> tranferir(@RequestBody TransacaoDTO transacaoDTO) throws Exception{
		service.tranferir(transacaoDTO);
		return ResponseEntity.ok().body(transacaoDTO);
	}

}
