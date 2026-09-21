package com.leilao.experience.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_leilao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_criador_id")
    private Usuario usuarioCriador;

    private BigDecimal lanceInicial;

    private BigDecimal maiorLanceAtual = BigDecimal.valueOf(0);

    private Long usuarioMaiorLanceId;

    private LocalDateTime dataInicio = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusLeilao statusLeilao = StatusLeilao.EM_ANDAMENTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vencedor_id")
    private Usuario vencedor;

}
