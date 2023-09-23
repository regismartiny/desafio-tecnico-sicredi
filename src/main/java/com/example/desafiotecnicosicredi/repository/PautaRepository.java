package com.example.desafiotecnicosicredi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiotecnicosicredi.entity.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {
}
