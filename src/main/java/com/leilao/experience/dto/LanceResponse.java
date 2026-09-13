package com.leilao.experience.dto;

import com.leilao.experience.entity.Lance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LanceResponse(
        Long lanceId,
        Long usuarioId,
        Long leilaoId,
        BigDecimal valor,
        LocalDateTime dataHora
) {
    public static LanceResponse fromEntity(Lance lance){
        return new LanceResponse(
                lance.getId(),
                lance.getUsuario().getId(),
                lance.getLeilao().getId(),
                lance.getValor(),
                lance.getDataHora()
        );
    }
}
