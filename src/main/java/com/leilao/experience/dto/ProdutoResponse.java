package com.leilao.experience.dto;

import com.leilao.experience.entity.CondicaoProduto;
import com.leilao.experience.entity.Produto;
import com.leilao.experience.entity.Usuario;

public record ProdutoResponse(
        Long id,
        String nome,
        Long usuarioId,
        String descricao,
        CondicaoProduto condicaoProduto
) {
    public static ProdutoResponse fromEntity(Produto produto){
        ProdutoResponse produtoResponse = new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getUsuario().getId(),
                produto.getDescricao(),
                produto.getCondicaoProduto()
        );
        return produtoResponse;
    }
}
