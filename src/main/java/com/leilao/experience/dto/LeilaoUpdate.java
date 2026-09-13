package com.leilao.experience.dto;

import java.math.BigDecimal;

public record LeilaoUpdate(
        BigDecimal valorMaiorLance,
        Long usuarioMaiorLanceId
) {
}
