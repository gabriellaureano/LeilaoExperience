package com.leilao.experience.dto;

import com.leilao.experience.entity.Usuario;

public record UsuarioRequest(String nome, String email, String senha) {

    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }
}
