package com.srm.wefin.strategy.impl.produto;

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
public class ConversaoProdutoStrategy implements ConversaoStrategy {

    private final MoedaService moedaService;
    private final TaxaCambioService taxaCambioService;

    @Override
    public BigDecimal fazerConversao(ConversaoStrategyParametros parametros) {

        validarParametros(parametros);
        Moeda origem = moedaService.buscarPorSigla(parametros.getMoedaOrigem());
        Moeda destino = moedaService.buscarPorSigla(parametros.getMoedaDestino());

        TaxaCambio taxaCambio = taxaCambioService.buscarPorMoedaOrigemMoedaDestino(origem.getSigla(), destino.getSigla());
        //NOTE: IMPLEMENTAR REGRAS COM BASE NO TIPO E REINO DO PRODUTO

        return parametros.getQuatidade().multiply(parametros.getValor()).multiply(taxaCambio.getTaxa());
    }

    @Override
    public void validarParametros(ConversaoStrategyParametros parametros){
        if(isNull(parametros.getProduto())){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Produto não informado.");
        }
        if(isNull(parametros.getMoedaOrigem()) || parametros.getMoedaOrigem().isBlank()){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Moeda de origem não informada.");
        }
        if(isNull(parametros.getMoedaDestino()) || parametros.getMoedaDestino().isBlank()){
            throw new BaseExcecao(HttpStatus.BAD_REQUEST, "Moeda de destino não informada.");
        }
    }
}
