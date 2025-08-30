package com.xpcosmos.transacoes_bancarias.assets;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User;

public class UserTestResource {
	ObjectMapper mapper = new ObjectMapper();


	public UserDTO gerarUserTestDTO(){
		String randomString = String.valueOf(LocalDateTime.now().hashCode());
		System.out.println(randomString);
		return  new UserDTO(
      "User Teste",
			randomString,
      "usertest" + randomString +"@gmail.com",
      "userteste12!@");
	}

	public String gerarUserTestDtoAsString() throws JsonProcessingException{
		return mapper.writeValueAsString(gerarUserTestDTO());
	}

	public User gerarUserTest(){
		return  new User(gerarUserTestDTO());
	}
}
