package com.leilao.experience.dto;

import com.leilao.experience.entity.CondicaoProduto;
import com.leilao.experience.entity.Produto;
import com.leilao.experience.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


public record ProdutoRequest(
        String nome,
        Long usuarioId,
        String descricao,
        CondicaoProduto condicaoProduto
) {

}
