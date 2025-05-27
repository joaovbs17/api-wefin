package com.srm.wefin.strategy.impl.moeda;

import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.model.taxacambio.TaxaCambio;
import com.srm.wefin.service.moeda.MoedaService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import com.srm.wefin.strategy.ConversaoStrategy;
import com.srm.wefin.strategy.impl.ConversaoStrategyParametros;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ConversaoMoedaStrategy implements ConversaoStrategy {

    private final MoedaService moedaService;
    private final TaxaCambioService taxaCambioService;

    @Override
    public BigDecimal fazerConversao(ConversaoStrategyParametros parametros) {

        validarParametros(parametros);
        Moeda origem = moedaService.buscarPorSigla(parametros.getMoedaOrigem());
        Moeda destino = moedaService.buscarPorSigla(parametros.getMoedaDestino());

        TaxaCambio taxaCambio = taxaCambioService.buscarPorMoedaOrigemMoedaDestino(origem.getSigla(), destino.getSigla());
        return parametros.getValor().multiply(taxaCambio.getTaxa());
    }

    @Override
    public void validarParametros(ConversaoStrategyParametros parametros) {
        if(isNull(parametros.getMoedaOrigem()) || parametros.getMoedaOrigem().isBlank()){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Moeda de origem não informada.");
        }
        if(isNull(parametros.getMoedaDestino()) || parametros.getMoedaDestino().isBlank()){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Moeda de destino não informada.");
        }
        if(isNull(parametros.getValor())){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Valor para conversão não informado.");
        }
    }
}
