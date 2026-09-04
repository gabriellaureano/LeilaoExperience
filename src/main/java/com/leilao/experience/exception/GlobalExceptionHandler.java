package com.leilao.experience.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity usuarioNaoEncontrado(UsuarioNaoEncontradoException erro){
        ErroResponse erroResponse = new ErroResponse(
                "Usuário Não Encontrado",
                HttpStatus.NOT_FOUND.value(),
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException erro){
        List<String> mensagens = erro.getBindingResult().getFieldErrors().stream()
                .map(ex ->  ex.getField() + ": " + ex.getDefaultMessage())
                .toList();

        ErroResponse erroResponse = new ErroResponse(
                "Erro de Validação",
                HttpStatus.BAD_REQUEST.value(),
                mensagens
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }
}
