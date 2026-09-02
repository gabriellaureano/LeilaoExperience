package com.leilao.experience.service;

import com.leilao.experience.dto.UsuarioRequest;
import com.leilao.experience.dto.UsuarioResponse;
import com.leilao.experience.entity.Usuario;
import com.leilao.experience.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse criarUsuario(UsuarioRequest request){

        Usuario usuario = request.toEntity();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuarioSalvo);
    }

    public List<UsuarioResponse> buscarUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    public UsuarioResponse buscarUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        return UsuarioResponse.fromEntity(usuario);
    }

    public UsuarioResponse atualizarUsuarioId(Long id, UsuarioRequest request){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

        usuarioExistente.setNome(request.nome());
        usuarioExistente.setEmail(request.email());
        usuarioExistente.setSenha(request.senha());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);

        return UsuarioResponse.fromEntity(usuarioAtualizado);
    }

    public void deletarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }
}
