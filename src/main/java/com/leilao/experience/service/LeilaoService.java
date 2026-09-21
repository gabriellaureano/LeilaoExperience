package com.leilao.experience.service;

import com.leilao.experience.dto.LeilaoFinalizarResponse;
import com.leilao.experience.dto.LeilaoRequest;
import com.leilao.experience.dto.LeilaoResponse;
import com.leilao.experience.dto.LeilaoUpdate;
import com.leilao.experience.entity.Leilao;
import com.leilao.experience.entity.Produto;
import com.leilao.experience.entity.StatusLeilao;
import com.leilao.experience.entity.Usuario;
import com.leilao.experience.exception.LeilaoEmAndamentoException;
import com.leilao.experience.exception.LeilaoNaoEncontradoException;
import com.leilao.experience.exception.ProdutoNaoEncontradoException;
import com.leilao.experience.exception.UsuarioNaoEncontradoException;
import com.leilao.experience.repository.LeilaoRepository;
import com.leilao.experience.repository.ProdutoRepository;
import com.leilao.experience.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public LeilaoResponse atualizarLeilaoPorId(Long id, LeilaoUpdate update){
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new LeilaoNaoEncontradoException("Leilão Não Encontrado"));

        leilao.setMaiorLanceAtual(update.valorMaiorLance());
        leilao.setUsuarioMaiorLanceId(update.usuarioMaiorLanceId());

        Leilao leilaoSalvo = leilaoRepository.save(leilao);

        return LeilaoResponse.fromEntity(leilaoSalvo);
    }

    public LeilaoFinalizarResponse finalizarLeilaoId(Long id){
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new LeilaoNaoEncontradoException("Leilão invalido ou Não Encontrado"));

        Usuario usuarioVencedor = usuarioRepository.findById(leilao.getUsuarioMaiorLanceId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario Não Encontrado"));

        if (leilao.getStatusLeilao() == StatusLeilao.EM_ANDAMENTO){
            leilao.setStatusLeilao(StatusLeilao.FINALIZADO);
            leilao.setVencedor(usuarioVencedor);

            Leilao leilaoSalvo = leilaoRepository.save(leilao);

            return new LeilaoFinalizarResponse(
                    leilaoSalvo.getId(),
                    leilaoSalvo.getUsuarioMaiorLanceId(),
                    usuarioVencedor.getNome(),
                    leilaoSalvo.getProduto().getId(),
                    leilaoSalvo.getProduto().getNome(),
                    leilao.getMaiorLanceAtual(),
                    LocalDateTime.now()
            );
        } else {
            throw new LeilaoEmAndamentoException("Não é possivel finalizar um leilão que não esta em andamento.");
        }

    }

    public LeilaoResponse cancelarLeilao(Long id){
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new LeilaoNaoEncontradoException("Leilão não encontrado"));

        if (leilao.getStatusLeilao() == StatusLeilao.EM_ANDAMENTO){
            leilao.setStatusLeilao(StatusLeilao.CANCELADO);

            Leilao leilaoSalvo = leilaoRepository.save(leilao);

            return LeilaoResponse.fromEntity(leilaoSalvo);
        } else {
            throw new LeilaoEmAndamentoException("Não é possivel cancelar um leilão que não está em andamento.");
        }
    }
}
