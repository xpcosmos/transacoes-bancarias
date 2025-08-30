package com.xpcosmos.transacoes_bancarias.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.xpcosmos.transacoes_bancarias.assets.TransferenciaTestResource;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;

@AutoConfigureMockMvc
@SpringBootTest
public class 	TranferenciaControllerTest extends TransferenciaTestResource {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	TransferenciaService transferenciaService;

	@Test
	void testTranferir() throws Exception{
		mockMvc.perform(post("/transfer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gerarStringDTO()));
	}
}
