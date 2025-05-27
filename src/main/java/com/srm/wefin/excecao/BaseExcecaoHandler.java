package com.srm.wefin.excecao;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BaseExcecaoHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExcecaoHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", exception);
        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> campoMensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (campo1, campo2) -> campo1
                ));

        LOGGER.error("Handle Exception ConstraintViolationException", exception);
        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", campoMensagem);

        return ResponseEntity.status(exception.getStatusCode()).body(campoMensagem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
    }

    @ExceptionHandler(BaseExcecao.class)
    protected ResponseEntity<Object> handleException(BaseExcecao exception) {

        LOGGER.error("Handle Exception", exception);
        LOGGER.info("Handle Exception Response: {}", exception.getMessage());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", exception.getStatus());
        resposta.put("mensagem", exception.getMensagem());

        return ResponseEntity.status(exception.getStatus()).body(resposta);
    }

}
