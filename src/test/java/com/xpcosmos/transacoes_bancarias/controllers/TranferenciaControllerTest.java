package com.xpcosmos.transacoes_bancarias.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.xpcosmos.transacoes_bancarias.assets.TransferenciaTestResource;
import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;
import com.xpcosmos.transacoes_bancarias.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class TranferenciaControllerTest extends TransferenciaTestResource {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	UserService userService;
	@MockitoBean
	TransferenciaService transferenciaService;

	UserTestResource userEntity = new UserTestResource();

	@Autowired
	EntityManager entityManager;

	User payee;
	User payer;

	@BeforeEach
	void setUp() throws Exception {

		payee = userEntity.gerarEntity();
		payee.setSaldo(20f);

		payer = userEntity.gerarEntity();
		payer.setSaldo(20f);

		entityManager.persist(payer);
		entityManager.persist(payee);
	}

	@Test
	@Transactional
	void testTransacion() throws Exception {
		when(userService.getUserById(payee.getId())).thenThrow(new RuntimeException());
		TransacaoDTO transacaoDTO = new TransacaoDTO(payee.getId(), payer.getId(), getValue());
		try {
			mockMvc.perform(
				post("/transfer").contentType(MediaType.APPLICATION_JSON).content(
					mapper.writeValueAsString(transacaoDTO)));
			MvcResult result = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
			String rList = result.getResponse().getContentAsString();
			assertEquals(mapper.writeValueAsString(List.of(payee, payer)), rList);

		} catch (Exception e) {
		}
		;
	}

	@Test
	void testTranferir() throws Exception {
		mockMvc.perform(post("/transfer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gerarStringDTO()));
	}
}
