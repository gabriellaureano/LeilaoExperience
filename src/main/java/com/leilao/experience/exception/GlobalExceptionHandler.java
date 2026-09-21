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

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity produtoNaoEncontrado(ProdutoNaoEncontradoException erro){
        ErroResponse erroResponse = new ErroResponse(
                "Produto Não Encontrado",
                HttpStatus.NOT_FOUND.value(),
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(LeilaoNaoEncontradoException.class)
    public ResponseEntity leilaoNaoEncontrado(LeilaoNaoEncontradoException erro){
        ErroResponse erroResponse = new ErroResponse(
                "Leilão Não Encontrado",
                HttpStatus.NOT_FOUND.value(),
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(LanceInvalidoException.class)
    public ResponseEntity lanceInvalido(LanceInvalidoException erro){
        ErroResponse erroResponse = new ErroResponse(
                "Realize um lance maior ou verifique se o leilao esta em andamento.",
                HttpStatus.BAD_REQUEST.value(),
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(LeilaoEmAndamentoException.class)
    public ResponseEntity leilaoEmAndamento(LeilaoEmAndamentoException erro){
        ErroResponse erroResponse = new ErroResponse(
                "Verifique se o leilão esta em andamento para prosseguir.",
                HttpStatus.BAD_REQUEST.value(),
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }


}
