package com.leilao.experience.dto;

import com.leilao.experience.entity.Leilao;
import com.leilao.experience.entity.StatusLeilao;
import org.aspectj.apache.bcel.generic.LineNumberGen;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LeilaoResponse(
        Long leilaoId,
        Long produtoId,
        Long usuarioCriadorId,
        BigDecimal lanceInicial,
        BigDecimal maiorLanceAtual,
        Long usuarioMaiorLanceId,
        LocalDateTime dataInicio,
        StatusLeilao statusLeilao
) {
    public static LeilaoResponse fromEntity(Leilao leilao){
        return new LeilaoResponse(
                leilao.getId(),
                leilao.getProduto().getId(),
                leilao.getUsuarioCriador().getId(),
                leilao.getLanceInicial(),
                leilao.getMaiorLanceAtual(),
                leilao.getUsuarioMaiorLanceId(),
                leilao.getDataInicio(),
                leilao.getStatusLeilao()
        );
    }
}
