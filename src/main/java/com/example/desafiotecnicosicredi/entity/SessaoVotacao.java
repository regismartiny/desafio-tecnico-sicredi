package com.example.desafiotecnicosicredi.entity;


import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "sessao_votacao_id_sessao_votacao_seq_generator", sequenceName = "sessao_votacao_seq", allocationSize = 1)
@Table(name = "sessao_votacao")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_votacao_id_sessao_votacao_seq_generator")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFimValidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private String usuario;

    @Version
    private Date versao;

}
