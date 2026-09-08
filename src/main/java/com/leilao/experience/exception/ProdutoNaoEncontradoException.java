package com.leilao.experience.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
