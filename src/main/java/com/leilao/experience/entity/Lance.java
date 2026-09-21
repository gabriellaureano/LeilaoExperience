package com.leilao.experience.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_lances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leilao_id")
    private Leilao leilao;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    @PrePersist
    public void prePersist(){
        this.dataHora = LocalDateTime.now();
    }
}
