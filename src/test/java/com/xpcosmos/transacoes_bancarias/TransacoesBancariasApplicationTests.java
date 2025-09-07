package com.xpcosmos.transacoes_bancarias;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootTest
@EnableTransactionManagement
@EnableSpringConfigured
@AutoConfigureTestDatabase
class TransacoesBancariasApplicationTests {

	@Test
	void contextLoads() {
	}

}
