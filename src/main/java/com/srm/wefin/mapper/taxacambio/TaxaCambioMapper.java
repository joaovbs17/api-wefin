package com.srm.wefin.mapper.taxacambio;

import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.model.taxacambio.TaxaCambio;
import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaxaCambioMapper {

    private final ModelMapper mapper;

    public TaxaCambioResponse resposta(TaxaCambio taxaCambio){
        TaxaCambioResponse resposta = mapper.map(taxaCambio, TaxaCambioResponse.class);
        return resposta;
    }

    public List<TaxaCambioResponse> resposta(List<TaxaCambio> taxaCambios){
        return taxaCambios.stream().map(this::resposta).collect(Collectors.toList());
    }

    public TaxaCambio criar(Moeda origem, Moeda destino, BigDecimal taxa){
        TaxaCambio taxaCambio = new TaxaCambio();
        taxaCambio.setMoedaOrigem(origem);
        taxaCambio.setMoedaDestino(destino);
        taxaCambio.setTaxa(taxa);
        taxaCambio.setDataHora(LocalDateTime.now());
        return taxaCambio;
    }

}
