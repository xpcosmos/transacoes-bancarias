package com.xpcosmos.transacoes_bancarias.controllers;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpcosmos.transacoes_bancarias.assets.UserTestCase;
import com.xpcosmos.transacoes_bancarias.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest extends UserTestCase {

  @Autowired
  MockMvc mockMvc;
  @Mock
  UserService userService;

  @Test
  void testUserCreation() throws Exception {

    ObjectMapper mapper = new ObjectMapper();

    mockMvc.perform(
        post("/user")
            .content(mapper.writeValueAsString(userDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());

  };

}
