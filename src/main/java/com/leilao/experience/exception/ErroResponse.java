package com.leilao.experience.exception;

import java.util.List;

public record ErroResponse(
        String erro,
        Integer status,
        List<String> erros
) {
}
