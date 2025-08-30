package com.xpcosmos.transacoes_bancarias.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest extends UserTestResource {

	@Autowired
	MockMvc mockMvc;
	@Mock
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
	void testResponseSenhaNull() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/user")
						.content(gerarStringDTO())
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertFalse(result.getResponse().getContentAsString().contains("senha"));
	};
}
