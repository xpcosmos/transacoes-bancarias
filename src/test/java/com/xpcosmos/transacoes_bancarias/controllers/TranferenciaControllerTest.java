package com.xpcosmos.transacoes_bancarias.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.xpcosmos.transacoes_bancarias.assets.TransferenciaTestResource;
import com.xpcosmos.transacoes_bancarias.assets.UserTestResource;
import com.xpcosmos.transacoes_bancarias.dto.UserDTO;
import com.xpcosmos.transacoes_bancarias.models.User.User;
import com.xpcosmos.transacoes_bancarias.services.TransferenciaService;
import com.xpcosmos.transacoes_bancarias.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TranferenciaControllerTest extends TransferenciaTestResource {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  EntityManager entityManager;

  @MockitoBean
  UserService userService;

  @MockitoBean
  TransferenciaService transferenciaService;

  UserTestResource userEntity = new UserTestResource();

  User pagador;
  User benficiario;

  UserDTO pagadorDTO;
  UserDTO benficiarioDTO;
  String transacaoDTO;

  @BeforeEach
  @Transactional(TxType.REQUIRES_NEW)
  void setUp() throws Exception {

    userEntity.setSaldo(122f);
    benficiario = userEntity.gerarEntity();

    userEntity.setSaldo(990f);
    pagador = userEntity.gerarEntity();

    entityManager.persist(benficiario);
    entityManager.persist(pagador);

    setPayeeId(benficiario.getId());
    setPayerId(pagador.getId());
    setValue(40f);
  }

  @AfterEach
  void cleanUp() throws Exception {
    entityManager.clear();
  };

  @Test
  @Transactional
  @DisplayName("""
      Teste em caso de erro em transacao
          """)
  void testTransacaoCasoErro() throws Exception {
    setPayerId(-1l);
    mockMvc.perform(
        post("/transfer")
            .content(gerarStringDTO())
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
    assertEquals(entityManager.find(User.class, benficiario.getId()).getSaldo(), benficiario.getSaldo());
    assertEquals(entityManager.find(User.class, pagador.getId()).getSaldo(), pagador.getSaldo());
  }

  // @Test
  // @Transactional(value = TxType.REQUIRES_NEW)
  // @DisplayName("""
  //     Teste em caso de sucesso em transacao
  //         """)
  // void testTransacaoCasoSucesso() throws Exception {
  //   String stringDTO = gerarStringDTO();
  //   mockMvc.perform(
  //       post("/transfer")
  //           .content(stringDTO)
  //           .contentType(MediaType.APPLICATION_JSON))
  //       .andReturn();

  //   assertEquals(result.getResponse().getContentAsString(), "");
  //   // assertNotEquals(entityManager.find(User.class, benficiario.getId()).getSaldo(), benficiario.getSaldo());
  //   assertNotEquals(entityManager.find(User.class, pagador.getId()).getSaldo(), pagador.getSaldo());
  // }

  @Test
  @Transactional
  void testTranferir() throws Exception {
    mockMvc.perform(post("/transfer")
        .contentType(MediaType.APPLICATION_JSON)
        .content(gerarStringDTO()));
  }


}
