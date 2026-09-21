package com.leilao.experience.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LeilaoFinalizarResponse(
        Long leilaoId,
        Long vencedorId,
        String nomeVencedor,
        Long produtoId,
        String nomeProduto,
        BigDecimal valorLanceContemplado,
        LocalDateTime dataFim
) {
}
