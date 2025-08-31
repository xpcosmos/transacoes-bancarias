package com.xpcosmos.transacoes_bancarias.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.xpcosmos.transacoes_bancarias.assets.TransferenciaTestResource;
import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;
import com.xpcosmos.transacoes_bancarias.services.UserService;

import jakarta.persistence.EntityManager;

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
	void testTranferir()throws Exception {
		mockMvc.perform(post("/transfer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gerarStringDTO()));
	}
}
