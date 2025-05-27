package com.srm.wefin.strategy.impl;

import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.model.produto.Produto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ConversaoStrategyParametros {

    private Produto produto;
    private String moedaOrigem;
    private String moedaDestino;
    private BigDecimal valor;
    private BigDecimal quatidade;
}
