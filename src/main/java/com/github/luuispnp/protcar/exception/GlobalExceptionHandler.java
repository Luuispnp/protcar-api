package com.github.luuispnp.protcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidacoes(MethodArgumentNotValidException ex) {

        List<ErroValidacaoResponse.CampoInvalido> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> new ErroValidacaoResponse.CampoInvalido(erro.getField(), erro.getDefaultMessage()))
                .collect(Collectors.toList());

        ErroValidacaoResponse resposta = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação nos dados enviados.",
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
