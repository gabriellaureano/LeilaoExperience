package com.leilao.experience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LeilaoRequest(
        @NotNull(message = "Id do produto é obrigatorio.")
        Long produtoId,
        @NotNull(message = "Id do usuario criador é obrigatorio.")
        Long usuarioCriadorId,
        @NotNull(message = "Lance inicial obrigatorio.")
        BigDecimal lanceInicial
) {
}
