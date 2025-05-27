package com.srm.wefin.repository.taxacambio;

import com.srm.wefin.model.taxacambio.TaxaCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, Long> {

    @Query("FROM TaxaCambio tc WHERE tc.moedaOrigem.sigla = :siglaOrigem AND tc.moedaDestino.sigla = :siglaDestino ORDER BY tc.dataHora DESC limit 1")
    Optional<TaxaCambio> buscarUltimaTaxa(@Param("siglaOrigem") String siglaOrigem, @Param("siglaDestino") String siglaDestino);

    List<TaxaCambio> findByMoedaOrigemSigla(String moedaOrigemSigla);

    Boolean existsByMoedaOrigemIdOrMoedaDestinoId(Long moedaOrigemId, Long moedaDestinoId);
}
