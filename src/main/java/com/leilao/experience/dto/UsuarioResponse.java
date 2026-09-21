package com.leilao.experience.dto;

import com.leilao.experience.entity.Usuario;

public record UsuarioResponse(Long id, String nome,String email) {

    public static UsuarioResponse fromEntity(Usuario usuario){
        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
        return usuarioResponse;
    }
}
