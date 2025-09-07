package com.xpcosmos.transacoes_bancarias.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xpcosmos.transacoes_bancarias.dto.NonCriticalExecptionDTO;
import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;
import com.xpcosmos.transacoes_bancarias.exceptions.DuplicateUserException;
import com.xpcosmos.transacoes_bancarias.exceptions.ExternalServiceException;
import com.xpcosmos.transacoes_bancarias.exceptions.InvalidOperationException;
import com.xpcosmos.transacoes_bancarias.exceptions.SendingNotificationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(DuplicateUserException.class)
  private ResponseEntity<String> duplicatedUserExceptions(DuplicateUserException exception){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(ExternalServiceException.class)
  private ResponseEntity<String> forbiddenResponse(ExternalServiceException exception){
    return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(exception.getMessage());
  }
  @ExceptionHandler(InvalidOperationException.class)
  private ResponseEntity<String> invalidOperationException(InvalidOperationException exception){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(SendingNotificationException.class)
  private ResponseEntity<NonCriticalExecptionDTO<TransacaoDTO>> sendingNotificationException(SendingNotificationException exception){
    NonCriticalExecptionDTO<TransacaoDTO> body = new NonCriticalExecptionDTO<>(
      exception.transacaoDTO,
      "Falha no envio de notiticação"
    );
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(body);

  }

}
