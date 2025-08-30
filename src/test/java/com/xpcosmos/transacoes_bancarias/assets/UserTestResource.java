package com.xpcosmos.transacoes_bancarias.assets;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.models.User.UserType;

public class UserTestResource {
	ObjectMapper mapper = new ObjectMapper();

	public UserDTO gerarUserTestDTO(Optional<UserType> userTypeOpt){
		UserType userType = userTypeOpt == null ? UserType.COMUM : userTypeOpt.get();
		String randomString = String.valueOf(LocalDateTime.now().hashCode());
		return  new UserDTO(
      "User Teste",
			randomString,
      "usertest" + randomString +"@gmail.com",
			userType	,
      "userteste12!@");
	}


	public String gerarUserTestDtoAsString(Optional<UserType> userTypeOpt) throws JsonProcessingException{
		return mapper.writeValueAsString(gerarUserTestDTO(userTypeOpt));
	}

	public User gerarUserTest(Optional<UserType> userTypeOpt){
		return  new User(gerarUserTestDTO(userTypeOpt));
	}
}
