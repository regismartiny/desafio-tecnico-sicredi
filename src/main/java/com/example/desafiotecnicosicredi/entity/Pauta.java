package com.example.desafiotecnicosicredi.entity;


import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@SequenceGenerator(name = "pauta_id_pauta_seq_generator", sequenceName = "pauta_seq", allocationSize = 1)
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_id_pauta_seq_generator")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<SessaoVotacao> sessoesVotacao;

    @Column(nullable = false)
    private String usuario;

    @Version
    private Date versao;
}
