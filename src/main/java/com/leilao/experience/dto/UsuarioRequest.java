package com.leilao.experience.dto;

import com.leilao.experience.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatorio.")
        String nome,
        @NotBlank(message = "Email é obrigatorio.")
        @Email(message = "Email inválido.")
        String email,
        @NotBlank(message = "Senha é obrigatoria.")
        String senha) {

    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }
}
