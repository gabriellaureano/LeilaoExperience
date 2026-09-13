package com.leilao.experience.dto;

import java.math.BigDecimal;

public record LanceRequest(
        Long usuarioId,
        Long leilaoId,
        BigDecimal valor
) {
}
