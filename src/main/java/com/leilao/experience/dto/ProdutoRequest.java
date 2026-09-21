package com.leilao.experience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



public record ProdutoRequest(
        @NotBlank(message = "Nome é obrigatorio.")
        String nome,
        @NotNull(message = "Id do Usuário é obrigatorio.")
        Long usuarioId,
        String descricao,
        @NotBlank(message = "Condição do produto é obrigatoria.")
        @Pattern(regexp = "^(NOVO|SEMINOVO|USADO)$",message = "Condição inválida. Os valores permitidos são: NOVO, SEMINOVO ou USADO")
        String condicaoProduto
) {

}
