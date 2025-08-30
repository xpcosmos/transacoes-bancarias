package com.xpcosmos.transacoes_bancarias.exceptions;

public class InvalidOperationException extends RuntimeException{

	public InvalidOperationException(){
		super("Operação inválida. Verifique os valores da requisição e tente novamente!");
	}

}
