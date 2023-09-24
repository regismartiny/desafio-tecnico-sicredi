package com.example.desafiotecnicosicredi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiotecnicosicredi.entity.Voto;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    @Query("select case when count(v)> 0 then true else false end from Voto v where v.cpfAssociado = ?1 and v.sessaoVotacao.pauta.id = ?2")
    boolean verificarSeUsuarioJaVotou(String cpfUsuario, Long idPauta);
}
