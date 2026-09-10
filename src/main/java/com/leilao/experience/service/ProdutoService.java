package com.leilao.experience.service;

import com.leilao.experience.dto.ProdutoRequest;
import com.leilao.experience.dto.ProdutoResponse;
import com.leilao.experience.dto.UsuarioResponse;
import com.leilao.experience.entity.CondicaoProduto;
import com.leilao.experience.entity.Produto;
import com.leilao.experience.entity.Usuario;
import com.leilao.experience.exception.ProdutoNaoEncontradoException;
import com.leilao.experience.exception.UsuarioNaoEncontradoException;
import com.leilao.experience.repository.ProdutoRepository;
import com.leilao.experience.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoResponse criarProduto(ProdutoRequest request){

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário Não Encontrado"));

        Produto produto = new Produto();
        produto.setUsuario(usuario);
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setCondicaoProduto(CondicaoProduto.valueOf(request.condicaoProduto()));

        Produto produtoSalvo = produtoRepository.save(produto);

        return ProdutoResponse.fromEntity(produtoSalvo);
    }

   public List<ProdutoResponse> buscarProdutos(){
        return produtoRepository.findAll().stream()
                .map(ProdutoResponse::fromEntity)
                .toList();
   }

   public ProdutoResponse buscarProdutoPorId(Long id){
        return produtoRepository.findById(id).map(ProdutoResponse::fromEntity)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto Não Encontrado"));
   }

   public ProdutoResponse atualizarProdutoPorId(Long id,ProdutoRequest produtoRequest){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto Não Encontrado"));

        Usuario usuario = usuarioRepository.findById(produtoRequest.usuarioId())
                        .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario Não Encontrado"));

        produtoExistente.setNome(produtoRequest.nome());
        produtoExistente.setUsuario(usuario);
        produtoExistente.setDescricao(produtoRequest.descricao());
        produtoExistente.setCondicaoProduto(CondicaoProduto.valueOf(produtoRequest.condicaoProduto()));

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);

        return ProdutoResponse.fromEntity(produtoAtualizado);
   }

   public void deletarProdutoPorId(Long id){
        produtoRepository.deleteById(id);   }
}
