package com.xpcosmos.transacoes_bancarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class TransacoesBancariasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacoesBancariasApplication.class, args);
	}

}
