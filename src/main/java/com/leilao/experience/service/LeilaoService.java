package com.leilao.experience.service;

import com.leilao.experience.dto.LeilaoRequest;
import com.leilao.experience.dto.LeilaoResponse;
import com.leilao.experience.entity.Leilao;
import com.leilao.experience.entity.Produto;
import com.leilao.experience.entity.Usuario;
import com.leilao.experience.exception.ProdutoNaoEncontradoException;
import com.leilao.experience.exception.UsuarioNaoEncontradoException;
import com.leilao.experience.repository.LeilaoRepository;
import com.leilao.experience.repository.ProdutoRepository;
import com.leilao.experience.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public LeilaoResponse adicionarNoLeilao(LeilaoRequest request){
        Produto produto = produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        Usuario usuarioCriador = usuarioRepository.findById(request.usuarioCriadorId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        Leilao leilao = new Leilao();
        leilao.setProduto(produto);
        leilao.setUsuarioCriador(usuarioCriador);
        leilao.setLanceInicial(request.lanceInicial());

        Leilao leilaoSalvo = leilaoRepository.save(leilao);

        return LeilaoResponse.fromEntity(leilaoSalvo);
    }

    public List<LeilaoResponse> buscarItensLeilao(){
        return leilaoRepository.findAll().stream()
                .map(LeilaoResponse::fromEntity)
                .toList();
    }
}
