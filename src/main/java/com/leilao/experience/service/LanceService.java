package com.leilao.experience.service;

import com.leilao.experience.dto.LanceRequest;
import com.leilao.experience.dto.LanceResponse;
import com.leilao.experience.dto.LeilaoUpdate;
import com.leilao.experience.entity.Lance;
import com.leilao.experience.entity.Leilao;
import com.leilao.experience.entity.Usuario;
import com.leilao.experience.exception.ErroResponse;
import com.leilao.experience.exception.LanceInvalidoException;
import com.leilao.experience.exception.LeilaoNaoEncontradoException;
import com.leilao.experience.exception.UsuarioNaoEncontradoException;
import com.leilao.experience.repository.LanceRepository;
import com.leilao.experience.repository.LeilaoRepository;
import com.leilao.experience.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanceService {
    private final LeilaoRepository leilaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LanceRepository lanceRepository;

    public ResponseEntity realizarLance(LanceRequest request){
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário Não Encontrado"));

        Leilao leilao = leilaoRepository.findById(request.leilaoId())
                .orElseThrow(()-> new LeilaoNaoEncontradoException("Leilão Não Encontrado"));

        if (request.valor().compareTo(leilao.getMaiorLanceAtual()) > 0){

            Lance lance = new Lance();
            lance.setUsuario(usuario);
            lance.setLeilao(leilao);
            lance.setValor(request.valor());

            Lance lanceSalvo = lanceRepository.save(lance);

            LeilaoUpdate updateDto = new LeilaoUpdate(
                    lanceSalvo.getValor(),
                    usuario.getId()
            );


            RestClient restClient = RestClient.create("http://localhost:8080");

            restClient.put()
                    .uri("/leilao/{id}",leilao.getId())
                    .body(updateDto)
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();

            return ResponseEntity.status(HttpStatus.CREATED).body(LanceResponse.fromEntity(lanceSalvo));
        }
        else{
            throw new LanceInvalidoException("Lance Invalido");
        }


    }

    public List<LanceResponse> buscarLances(){
        return lanceRepository.findAll().stream()
                .map(LanceResponse::fromEntity)
                .toList();
    }
}
