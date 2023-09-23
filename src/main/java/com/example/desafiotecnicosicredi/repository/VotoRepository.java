package com.example.desafiotecnicosicredi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiotecnicosicredi.entity.Voto;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {
}
