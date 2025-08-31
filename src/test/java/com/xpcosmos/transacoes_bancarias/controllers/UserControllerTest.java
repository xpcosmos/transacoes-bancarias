package com.xpcosmos.transacoes_bancarias.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest extends UserTestResource {

	@Autowired
	MockMvc mockMvc;
	@MockitoBean
	UserService userService;

	@Test
	void testUserCreation() throws Exception {
		mockMvc.perform(
				post("/user")
						.content(gerarStringDTO())
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
	};

	@Test
	void testGet() throws Exception {
		List<User> entities = List.of(gerarEntity(), gerarEntity());
		String stringValues = mapper.writeValueAsString(entities);
		when(userService.getAllUsers()).thenReturn(entities);
		mockMvc.perform(get("/user")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						content().string(stringValues));
	}

	@Test
	void testResponseSenhaNull() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/user")
						.content(gerarStringDTO())
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertFalse(result.getResponse().getContentAsString().contains("senha"));
	};
}
