package com.xpcosmos.transacoes_bancarias.assets;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class TestResource<T>{

	ObjectMapper mapper = new ObjectMapper();
	String randomString() {return String.valueOf(LocalDateTime.now().hashCode());}

	public abstract T gerarDTO();
	public String gerarStringDTO() throws JsonProcessingException{return mapper.writeValueAsString(gerarDTO());};
}
