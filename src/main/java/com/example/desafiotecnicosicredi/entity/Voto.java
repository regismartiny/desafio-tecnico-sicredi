package com.example.desafiotecnicosicredi.entity;


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
@SequenceGenerator(name = "voto_id_voto_seq_generator", sequenceName = "voto_seq", allocationSize = 1)
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_id_voto_seq_generator")
    private Long id;

    @Column(name = "cpf_associado", nullable = false)
    private String cpfAssociado;

    @Column(name = "opcao_voto", nullable = false)
    private Integer opcaoVoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sessao_votacao", nullable = false)
    private SessaoVotacao sessaoVotacao;

    @Column(nullable = false)
    private String usuario;

    @Version
    private Date versao;
}
