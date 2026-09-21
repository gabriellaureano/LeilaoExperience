package com.leilao.experience.dto;

import java.math.BigDecimal;

public record LeilaoRequest(
        Long produtoId,
        Long usuarioCriadorId,
        BigDecimal lanceInicial
) {
}
