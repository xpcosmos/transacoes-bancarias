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

	public UserDTO gerarUserTestDTO(Optional<UserType> userTypeOpt, Optional<Float> saldoOptional){
		UserType userType = userTypeOpt == null ? UserType.COMUM : userTypeOpt.get();
		Float saldo = saldoOptional == null ? 0f : saldoOptional.get();
		String randomString = String.valueOf(LocalDateTime.now().hashCode());
		return  new UserDTO(
      "User Teste",
			randomString,
      "usertest" + randomString +"@gmail.com",
			userType,
			saldo,
      "userteste12!@");
	}


	public String gerarUserTestDtoAsString(Optional<UserType> userTypeOpt, Optional<Float> saldoOptional) throws JsonProcessingException{
		return mapper.writeValueAsString(gerarUserTestDTO(userTypeOpt, saldoOptional));
	}

	public User gerarUserTest(Optional<UserType> userTypeOpt,Optional<Float> saldoOptional){
		return  new User(gerarUserTestDTO(userTypeOpt, saldoOptional));
	}
}
