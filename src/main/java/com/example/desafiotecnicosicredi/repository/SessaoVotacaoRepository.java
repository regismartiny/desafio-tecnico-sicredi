package com.example.desafiotecnicosicredi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiotecnicosicredi.entity.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository extends CrudRepository<SessaoVotacao, Long> {
}
