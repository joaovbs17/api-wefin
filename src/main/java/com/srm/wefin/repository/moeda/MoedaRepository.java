package com.srm.wefin.repository.moeda;

import com.srm.wefin.model.moeda.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoedaRepository extends JpaRepository<Moeda, Long> {

    Boolean existsBySigla(String sigla);

    @Query("SELECT count(id) > 0 FROM Moeda WHERE LOWER(nome) = LOWER(:nome)")
    Boolean existsByNome(String nome);

    Optional<Moeda> findBySigla(String sigla);

    Boolean existsByReinoOrigemId(Long reinoOrigemId);
}
